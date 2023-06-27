package com.jsp1.SpringBootProject.Helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.jsp1.SpringBootProject.DTO.RecordDetails;

public class Helper {

	//check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }


    //convert excel to list of Records

    public static List<RecordDetails> convertExcelToListOfProduct(InputStream is) {
        List<RecordDetails> list = new ArrayList<>();

        try {

//        	Creating an workbook object and storing the input workbook
            XSSFWorkbook workbook = new XSSFWorkbook(is);

//          In sheet reference we are storing the Sheet1 that we will get form Input excel file
            XSSFSheet sheet = workbook.getSheet("Sheet1");

//            To iterate over the rows of sheet we are using Iterator
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

//                To iterate over the Cells of sheet we are using Iterator
                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                RecordDetails p = new RecordDetails();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:		// cell 0
                            p.setName(cell.getStringCellValue());
                            break;
                        case 1:		// cell 1
                            p.setPhoneNumber((long) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(p);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

}
