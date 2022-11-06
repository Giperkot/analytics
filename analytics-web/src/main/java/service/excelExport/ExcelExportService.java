package service.excelExport;

import core.rest.RequestHelper;
import db.entity.realty.excelimport.ImportRealtyObjectEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class ExcelExportService {
    private static final ExcelExportService instance = new ExcelExportService();

    public static ExcelExportService getInstance() {
        return instance;
    }

    private ExcelExportService() {
    }

    public void exportExcel(RequestHelper requestHelper, List<ImportRealtyObjectEntity> entityList) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Аналоги");
        sheet.setColumnWidth(0, 250);
        sheet.setColumnWidth(1, 125);
        sheet.setColumnWidth(2, 150);
        sheet.setColumnWidth(3, 120);
        sheet.setColumnWidth(4, 110);
        sheet.setColumnWidth(5, 140);
        sheet.setColumnWidth(6, 165);
        sheet.setColumnWidth(7, 140);
        sheet.setColumnWidth(8, 170);
        sheet.setColumnWidth(9, 300);
        sheet.setColumnWidth(10, 175);
        sheet.setColumnWidth(11, 120);

        Row header = sheet.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Местоположение");

        headerCell = header.createCell(1);
        headerCell.setCellValue("Количество комнат");

        headerCell = header.createCell(2);
        headerCell.setCellValue("Сегмент");

        headerCell = header.createCell(3);
        headerCell.setCellValue("Этажность дома");

        headerCell = header.createCell(4);
        headerCell.setCellValue("Материал стен");

        headerCell = header.createCell(5);
        headerCell.setCellValue("Этаж расположения");

        headerCell = header.createCell(6);
        headerCell.setCellValue("Площадь квартиры, кв.м");

        headerCell = header.createCell(7);
        headerCell.setCellValue("Площадь кухни, кв.м");

        headerCell = header.createCell(8);
        headerCell.setCellValue("Наличие балкона/лоджии");

        headerCell = header.createCell(9);
        headerCell.setCellValue("Удаленность от станции метро, мин. пешком");

        headerCell = header.createCell(10);
        headerCell.setCellValue("Состояние");

        headerCell = header.createCell(11);
        headerCell.setCellValue("Сумма");

        for (int i = 0; i < entityList.size(); i++) {
            Row row = sheet.createRow(i + 1);

            Cell cell = row.createCell(0);
            cell.setCellValue(entityList.get(i).getAddress());

            cell = row.createCell(1);
            cell.setCellValue(entityList.get(i).getRoomsCount().getTitle());

            cell = row.createCell(2);
            cell.setCellValue(entityList.get(i).getRealtySegment().getTitle());

            cell = row.createCell(3);
            cell.setCellValue(entityList.get(i).getHouseFloorsCount());

            cell = row.createCell(4);
            cell.setCellValue(entityList.get(i).getWallMaterial().getTitle());

            cell = row.createCell(5);
            cell.setCellValue(entityList.get(i).getFloor());

            cell = row.createCell(6);
            cell.setCellValue(entityList.get(i).getTotalArea());

            cell = row.createCell(7);
            cell.setCellValue(entityList.get(i).getKitchenArea());

            cell = row.createCell(8);
            cell.setCellValue(entityList.get(i).getBalcon().getTitle());

            cell = row.createCell(9);
            cell.setCellValue(entityList.get(i).getMetroDistance());

            cell = row.createCell(10);
            cell.setCellValue(entityList.get(i).getRepairType().getTitle());

//            cell = row.createCell(11);
//            cell.setCellValue(entityList.get(i).getSum());
        }

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "Аналоги.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }
}
