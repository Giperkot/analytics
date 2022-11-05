package service.excelReader;

import converter.realty.ImportMapper;
import converter.realty.ImportMapperImpl;
import core.rest.RequestHelper;
import dao.realty.excelimport.ImportRealtyDao;
import db.entity.realty.excelimport.ImportRealtyObjectEntity;
import db.entity.realty.excelimport.ImportRealtyRequestEntity;
import dto.realty.excelimport.ImportExcelRealtyDto;
import dto.realty.excelimport.ImportResponseDto;
import enums.report.EBalconParam;
import enums.report.ERealtySegment;
import enums.report.ERepairType;
import enums.report.ESimpleHouseType;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelReaderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReaderService.class);

    private static final ExcelReaderService instance = new ExcelReaderService();

    public static ExcelReaderService getInstance() {
        return instance;
    }

    private final ImportMapper importMapper = new ImportMapperImpl();

    private final ImportRealtyDao importRealtyDao = ImportRealtyDao.getInstance();

    private ExcelReaderService() {
    }

    public ImportResponseDto readFromExcel(RequestHelper requestHelper, List<Part> partsList) throws IOException {

        for (Part part : partsList) {
            String fileName = part.getSubmittedFileName();

            if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
                throw new RuntimeException("Необходимо загрузить файл в формате xls или xlsx");
            }

            if (fileName.endsWith("xls")) {
                return readFromXLSFile(requestHelper, part.getInputStream());
            }

            if (fileName.endsWith("xlsx")) {
                return readFromXLSXFile(requestHelper, part.getInputStream());
            }
        }

        throw new RuntimeException("Необходимо загрузить файл в формате xls или xlsx");
    }

    private ImportResponseDto readFromXLSFile(RequestHelper requestHelper, InputStream inputStream) throws IOException {
        try (HSSFWorkbook excelBook = new HSSFWorkbook(inputStream);) {
            HSSFSheet excelSheet = excelBook.getSheetAt(0);
            return readRowsFromExcel(requestHelper, excelSheet);
        }

    }

    private ImportResponseDto readFromXLSXFile(RequestHelper requestHelper, InputStream inputStream) throws IOException {
        try (XSSFWorkbook excelBook = new XSSFWorkbook(inputStream);) {
            XSSFSheet excelSheet = excelBook.getSheetAt(0);
            return readRowsFromExcel(requestHelper, excelSheet);
        }
    }

    private ImportResponseDto readRowsFromExcel(RequestHelper requestHelper, Sheet sheet) {
        int firstIdx = -1;
        for (int i = 0; ;i++) {
            if (sheet.getRow(i).getCell(0) == null) {
                continue;
            }

            if (sheet.getRow(i).getCell(0).getStringCellValue().contains("Местоположение")) {
                firstIdx = i;
                break;
            }
        }

        List<ImportRealtyObjectEntity> realtyObjectEntityList = new ArrayList<>();

        // todo Нужно сделать валидацию.
        for (int i = firstIdx + 1; ; i++) {
            Row row = sheet.getRow(i);

            if (row == null) {
                break;
            }

            ImportExcelRealtyDto excelRealtyDto = new ImportExcelRealtyDto();

            if (row.getCell(0).getCellType() == CellType.STRING) {
                String location = row.getCell(0).getStringCellValue();
                excelRealtyDto.setAddress(location);
            }
            if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                int rooms = (int) row.getCell(1).getNumericCellValue();
                excelRealtyDto.setRoomsCount(String.valueOf(rooms));
            }
            if (row.getCell(1).getCellType() == CellType.STRING) {
                String rooms = row.getCell(1).getStringCellValue();
                excelRealtyDto.setRoomsCount(rooms);
            }

            if (row.getCell(2).getCellType() == CellType.STRING) {
                String buildingType = row.getCell(2).getStringCellValue();
                excelRealtyDto.setRealtySegment(ERealtySegment.getByTitle(buildingType));
            }
            if (row.getCell(3).getCellType() == CellType.NUMERIC) {
                int floorsInBuilding = (int) row.getCell(3).getNumericCellValue();
                excelRealtyDto.setHouseFloorsCount(floorsInBuilding);
            }
            if (row.getCell(4).getCellType() == CellType.STRING) {
                String wallsMaterial = row.getCell(4).getStringCellValue();
                excelRealtyDto.setWallMaterial(ESimpleHouseType.getByTitle(wallsMaterial));
            }
            if (row.getCell(5).getCellType() == CellType.NUMERIC) {
                int floor = (int) row.getCell(5).getNumericCellValue();
                excelRealtyDto.setFloor(floor);
            }
            if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                double apartmentArea = row.getCell(6).getNumericCellValue();
                excelRealtyDto.setTotalArea(apartmentArea);
            }
            if (row.getCell(7).getCellType() == CellType.NUMERIC) {
                double kitchenArea = (float) row.getCell(7).getNumericCellValue();
                excelRealtyDto.setKitchenArea(kitchenArea);
            }
            if (row.getCell(8).getCellType() == CellType.STRING) {
                String balcony = row.getCell(8).getStringCellValue();
                excelRealtyDto.setBalcon(EBalconParam.getByTitle(balcony));
            }
            if (row.getCell(9).getCellType() == CellType.NUMERIC) {
                int distanceToMetro = (int) row.getCell(9).getNumericCellValue();
                excelRealtyDto.setMetroDistance(distanceToMetro);
            }
            if (row.getCell(10).getCellType() == CellType.STRING) {
                String condition = row.getCell(10).getStringCellValue();
                excelRealtyDto.setRepairType(ERepairType.getByTitle(condition));
            }

            realtyObjectEntityList.add(importMapper.toImportRealtyObjectEntity(excelRealtyDto));
        }

        ImportRealtyRequestEntity importRealtyRequestEntity = new ImportRealtyRequestEntity();
        importRealtyRequestEntity.setUserId(requestHelper.getUserInfo().getId());
        importRealtyRequestEntity.setObjectsCount(realtyObjectEntityList.size());

        Connection connection = requestHelper.getConnection();

        try {
            // Сохранить это в БД.
            importRealtyDao.saveImportRealtyRequest(connection, importRealtyRequestEntity, realtyObjectEntityList);

            realtyObjectEntityList = importRealtyDao.getImportRealtyObjectsByRequest(connection, importRealtyRequestEntity.getId());

            connection.commit();
        } catch (SQLException exception) {
            LOGGER.error("Ошибка при сохранении запроса на импорт", exception);
            throw new RuntimeException("Ошибка при сохранении запроса на импорт", exception);
        }

        List<ImportExcelRealtyDto> realtyDtoList = realtyObjectEntityList.stream()
                                                                         .map((elm) -> importMapper.toImportExcelRealtyDto(elm))
                                                                         .collect(Collectors.toList());

        ImportResponseDto importResponseDto = new ImportResponseDto();
        importResponseDto.setImportExcelRealtyDtoList(realtyDtoList);
        importResponseDto.setRequestId(importRealtyRequestEntity.getId());

        return importResponseDto;
    }

    public ImportMapper getImportMapper() {
        return importMapper;
    }
}
