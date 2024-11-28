import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Scholarship {
    public static void main(String[] args) {
        String inputFilePath = "students.xlsx";
        String outputFilePath = "updated_students.xlsx";

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             Workbook workbook = new XSSFWorkbook(fis);
             Workbook updatedWorkbook = new XSSFWorkbook()) {

            Sheet inputSheet = workbook.getSheetAt(0);
            Sheet updatedSheet = updatedWorkbook.createSheet("Updated Students");
            Sheet averagesSheet = updatedWorkbook.createSheet("Scholarship Averages");

            Row inputHeader = inputSheet.getRow(0);
            Row updatedHeader = updatedSheet.createRow(0);

            for (int i = 0; i < inputHeader.getLastCellNum(); i++) {
                updatedHeader.createCell(i).setCellValue(inputHeader.getCell(i).getStringCellValue());
            }
            updatedHeader.createCell(inputHeader.getLastCellNum()).setCellValue("New Scholarship");

            double engineeringTotal = 0, engineeringCount = 0;
            double economicsTotal = 0, economicsCount = 0;
            double philosophyTotal = 0, philosophyCount = 0;
            double marketingTotal = 0, marketingCount = 0;

            for (int i = 1; i <= inputSheet.getLastRowNum(); i++) {
                Row row = inputSheet.getRow(i);
                if (row == null) continue;

                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String group = row.getCell(2).getStringCellValue();
                double scholarship = row.getCell(3).getNumericCellValue();
                double gpa = row.getCell(4).getNumericCellValue();
                String faculty = row.getCell(5).getStringCellValue();

                double newScholarship = scholarship;
                if (faculty.equals("Engineering") && gpa > 2.4) {
                    newScholarship += scholarship * 0.10;
                    engineeringTotal += newScholarship - scholarship;
                    engineeringCount++;
                } else if (faculty.equals("Economics") && gpa > 2.4) {
                    newScholarship += scholarship * 0.15;
                    economicsTotal += newScholarship - scholarship;
                    economicsCount++;
                } else if (faculty.equals("Philosophy") && gpa > 2.2) {
                    newScholarship += scholarship * 0.05;
                    philosophyTotal += newScholarship - scholarship;
                    philosophyCount++;
                } else if (faculty.equals("Marketing") && gpa > 2.5) {
                    newScholarship += scholarship * 0.08;
                    marketingTotal += newScholarship - scholarship;
                    marketingCount++;
                }

                Row updatedRow = updatedSheet.createRow(i);
                updatedRow.createCell(0).setCellValue(id);
                updatedRow.createCell(1).setCellValue(name);
                updatedRow.createCell(2).setCellValue(group);
                updatedRow.createCell(3).setCellValue(scholarship);
                updatedRow.createCell(4).setCellValue(gpa);
                updatedRow.createCell(5).setCellValue(faculty);
                updatedRow.createCell(6).setCellValue(newScholarship);
            }

            Row headerRow = averagesSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Faculty");
            headerRow.createCell(1).setCellValue("Average Scholarship Increase");

            if (engineeringCount > 0) {
                Row row = averagesSheet.createRow(1);
                row.createCell(0).setCellValue("Engineering");
                row.createCell(1).setCellValue(engineeringTotal / engineeringCount);
            }
            if (economicsCount > 0) {
                Row row = averagesSheet.createRow(2);
row.createCell(0).setCellValue("Economics");
                row.createCell(1).setCellValue(economicsTotal / economicsCount);
            }
            if (philosophyCount > 0) {
                Row row = averagesSheet.createRow(3);
                row.createCell(0).setCellValue("Philosophy");
                row.createCell(1).setCellValue(philosophyTotal / philosophyCount);
            }
            if (marketingCount > 0) {
                Row row = averagesSheet.createRow(4);
                row.createCell(0).setCellValue("Marketing");
                row.createCell(1).setCellValue(marketingTotal / marketingCount);
            }

            try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                updatedWorkbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} //Azatkyzy Zeinep SIS_2
