package service.excelReader;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReaderService {
    private static final ExcelReaderService instance = new ExcelReaderService();

    public static ExcelReaderService getInstance() {
        return instance;
    }

    public void readFromExcel(File file) throws IOException {
        String fileName = file.getName();
        if (fileName.substring(fileName.lastIndexOf(".")).equals("xls")) {
            readFromXLSFile(file);
        } else if (fileName.substring(fileName.lastIndexOf(".")).equals("xlsx")) {
            readFromXLSXFile(file);
        } else {
            System.out.println("Can't read this file type");
        }
    }

    private void readFromXLSFile(File file) throws IOException {
        HSSFWorkbook excelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet excelSheet = excelBook.getSheetAt(0);
        readRowsFromExcel(excelSheet);
        excelBook.close();
    }

    private void readFromXLSXFile(File file) throws IOException {
        XSSFWorkbook excelBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet excelSheet = excelBook.getSheetAt(0);
        readRowsFromExcel(excelSheet);
        excelBook.close();
    }

    private void readRowsFromExcel(Sheet sheet) {
        for (Row row : sheet) {
            if (row.getCell(0).getCellType() == CellType.STRING) {
                String location = row.getCell(0).getStringCellValue();
                System.out.println("location : " + location);
            }
            if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                Integer rooms = (int) row.getCell(1).getNumericCellValue();
                System.out.println("rooms :" + rooms);
            }
            if (row.getCell(2).getCellType() == CellType.STRING) {
                String buildingType = row.getCell(2).getStringCellValue();
                System.out.println("buildingType : " + buildingType);
            }
            if (row.getCell(3).getCellType() == CellType.NUMERIC) {
                Integer floorsInBuilding = (int) row.getCell(3).getNumericCellValue();
                System.out.println("floorsInBuilding :" + floorsInBuilding);
            }
            if (row.getCell(4).getCellType() == CellType.STRING) {
                String wallsMaterial = row.getCell(4).getStringCellValue();
                System.out.println("wallsMaterial : " + wallsMaterial);
            }
            if (row.getCell(5).getCellType() == CellType.NUMERIC) {
                Integer floor = (int) row.getCell(5).getNumericCellValue();
                System.out.println("floor :" + floor);
            }
            if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                Float apartmentArea = (float) row.getCell(6).getNumericCellValue();
                System.out.println("apartmentArea :" + apartmentArea);
            }
            if (row.getCell(7).getCellType() == CellType.NUMERIC) {
                Float kitchenArea = (float) row.getCell(7).getNumericCellValue();
                System.out.println("kitchenArea :" + kitchenArea);
            }
            if (row.getCell(8).getCellType() == CellType.BOOLEAN) {
                Boolean balcony = row.getCell(8).getBooleanCellValue();
                System.out.println("balcony :" + balcony);
            }
            if (row.getCell(9).getCellType() == CellType.NUMERIC) {
                Integer distanceToMetro = (int) row.getCell(9).getNumericCellValue();
                System.out.println("distanceToMetro :" + distanceToMetro);
            }
            if (row.getCell(10).getCellType() == CellType.STRING) {
                String condition = row.getCell(10).getStringCellValue();
                System.out.println("condition : " + condition);
            }
        }
    }
}
