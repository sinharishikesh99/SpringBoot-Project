package com.jsp1.SpringBootProject.Service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp1.SpringBootProject.DTO.RecordDetails;
import com.jsp1.SpringBootProject.Repository.RecordRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

// Exporting to ExcelSheet

@Service
public class ReportService {

	@Autowired
	private RecordRepo recordRepository;

	public void generateExcel(HttpServletResponse response) throws Exception {

		List<RecordDetails> records = recordRepository.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Records Info");   // "Records Info" is the name of the sheet that we will be generate
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("Name");
		row.createCell(1).setCellValue("Phone_Number");

		int dataRowIndex = 1;

		for (RecordDetails record : records) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(record.getName());
			dataRow.createCell(1).setCellValue(record.getPhoneNumber());
			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();  // creating an ops to store the output and pass as response
		workbook.write(ops);		// whatever data is present in workbook i am passing to ServletOutputStream ops
		workbook.close();
		ops.close();

	}
}
