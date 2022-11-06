package service.excelReader;

import converter.realty.ImportMapper;
import converter.realty.ImportMapperImpl;
import core.rest.RequestHelper;
import dao.realty.excelimport.ImportRealtyDao;
import db.entity.realty.excelimport.ImportRealtyObjectEntity;
import db.entity.realty.excelimport.ImportRealtyRequestEntity;
import dto.realty.excelimport.ImportExcelRealtyDto;
import dto.realty.excelimport.ImportResponseDto;
import dto.realty.excelimport.ShowImportExcelRealtyDto;
import enums.report.*;
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
        List<ShowImportExcelRealtyDto> excelRealtyDtoToShowList = new ArrayList<>();

        // todo Нужно сделать валидацию.
        for (int i = firstIdx + 1; ; i++) {
            Row row = sheet.getRow(i);

            if (row == null) {
                break;
            }

            ImportExcelRealtyDto excelRealtyDto = new ImportExcelRealtyDto();
            ShowImportExcelRealtyDto excelRealtyDtoToShow = new ShowImportExcelRealtyDto();

            try {
                if (row.getCell(0).getCellType() == CellType.STRING) {
                    String location = row.getCell(0).getStringCellValue();
                    if (location.replaceAll("\\s", "").length() == 0) {
                        throw new IllegalArgumentException("В строке " + (i+1) + " столбца Местоположение найдена пустая ячейка");
                    }
                    excelRealtyDto.setAddress(location);
                    excelRealtyDtoToShow.setAddress(location);
                }

                if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                    int rooms = (int) row.getCell(1).getNumericCellValue();
                    ERoomsCount.getByTitle(String.valueOf(rooms), i+1);
                    excelRealtyDto.setRoomsCount(String.valueOf(rooms));
                    excelRealtyDtoToShow.setRoomsCount(String.valueOf(rooms));
                } else if (row.getCell(1).getCellType() == CellType.STRING) {
                    String rooms = row.getCell(1).getStringCellValue();
                    ERoomsCount.getByTitle(rooms, i+1);
                    excelRealtyDto.setRoomsCount(rooms);
                    excelRealtyDtoToShow.setRoomsCount(rooms);
                }

                if (row.getCell(2).getCellType() == CellType.STRING) {
                    String buildingType = row.getCell(2).getStringCellValue();
                    excelRealtyDto.setRealtySegment(ERealtySegment.getByTitle(buildingType, i+1));
                    excelRealtyDtoToShow.setRealtySegment(buildingType);
                }

                if (row.getCell(3).getCellType() == CellType.NUMERIC) {
                    int floorsInBuilding = (int) row.getCell(3).getNumericCellValue();
                    excelRealtyDto.setHouseFloorsCount(floorsInBuilding);
                    excelRealtyDtoToShow.setHouseFloorsCount(floorsInBuilding);
                } else {
                    throw new IllegalArgumentException("В столбце Этажность дома в строке " + (i+1) + " ожидается числовое значение");
                }

                if (row.getCell(4).getCellType() == CellType.STRING) {
                    String wallsMaterial = row.getCell(4).getStringCellValue();
                    excelRealtyDto.setWallMaterial(ESimpleHouseType.getByTitle(wallsMaterial, i+1));
                    excelRealtyDtoToShow.setWallMaterial(wallsMaterial);
                }

                if (row.getCell(5).getCellType() == CellType.NUMERIC) {
                    int floor = (int) row.getCell(5).getNumericCellValue();
                    excelRealtyDto.setFloor(floor);
                    excelRealtyDtoToShow.setFloor(floor);
                } else {
                    throw new IllegalArgumentException("В столбце Этаж расположения в строке " + (i+1) + " ожидается числовое значение");
                }

                if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                    double apartmentArea = row.getCell(6).getNumericCellValue();
                    excelRealtyDto.setTotalArea(apartmentArea);
                    excelRealtyDtoToShow.setTotalArea(apartmentArea);
                } else {
                    throw new IllegalArgumentException("В столбце Площадь квартиры в строке " + (i+1) + " ожидается числовое значение");
                }

                if (row.getCell(7).getCellType() == CellType.NUMERIC) {
                    double kitchenArea = row.getCell(7).getNumericCellValue();
                    excelRealtyDto.setKitchenArea(kitchenArea);
                    excelRealtyDtoToShow.setKitchenArea(kitchenArea);
                } else {
                    throw new IllegalArgumentException("В столбце Площадь кухни в строке " + (i+1) + " ожидается числовое значение");
                }

                if (row.getCell(8).getCellType() == CellType.STRING) {
                    String balcony = row.getCell(8).getStringCellValue();
                    excelRealtyDto.setBalcon(EBalconParam.getByTitle(balcony, i+1));
                    excelRealtyDtoToShow.setBalcon(balcony);
                }

                if (row.getCell(9).getCellType() == CellType.NUMERIC) {
                    int distanceToMetro = (int) row.getCell(9).getNumericCellValue();
                    excelRealtyDto.setMetroDistance(distanceToMetro);
                    excelRealtyDtoToShow.setMetroDistance(distanceToMetro);
                } else {
                    throw new IllegalArgumentException("В столбце Удаленность от станции метро в строке " + (i+1) + " ожидается числовое значение");
                }

                if (row.getCell(10).getCellType() == CellType.STRING) {
                    String condition = row.getCell(10).getStringCellValue();
                    excelRealtyDto.setRepairType(ERepairType.getByTitle(condition, i+1));
                    excelRealtyDtoToShow.setRepairType(condition);
                }
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (NullPointerException e) {
                throw new RuntimeException("В строке " + (i+1) + " найдена пустая ячейка");
            }

            realtyObjectEntityList.add(importMapper.toImportRealtyObjectEntity(excelRealtyDto));
            excelRealtyDtoToShowList.add(excelRealtyDtoToShow);
        }

        ImportRealtyRequestEntity importRealtyRequestEntity = new ImportRealtyRequestEntity();
        importRealtyRequestEntity.setUserId(requestHelper.getUserInfo().getId());
        importRealtyRequestEntity.setObjectsCount(realtyObjectEntityList.size());

        Connection connection = requestHelper.getConnection();

        try {
            // Сохранить это в БД.
            importRealtyDao.saveImportRealtyRequest(connection, importRealtyRequestEntity, realtyObjectEntityList);
            connection.commit();
        } catch (SQLException exception) {
            LOGGER.error("Ошибка при сохранении запроса на импорт", exception);
            throw new RuntimeException("Ошибка при сохранении запроса на импорт", exception);
        }

        ImportResponseDto importResponseDto = new ImportResponseDto();
        importResponseDto.setImportExcelRealtyDtoList(excelRealtyDtoToShowList);
        importResponseDto.setRequestId(importRealtyRequestEntity.getId());

        return importResponseDto;
    }

    public ImportMapper getImportMapper() {
        return importMapper;
    }
}
