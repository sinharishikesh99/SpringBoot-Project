package com.jsp1.SpringBootProject.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp1.SpringBootProject.DAO.RecordDao;
import com.jsp1.SpringBootProject.DTO.RecordDetails;
import com.jsp1.SpringBootProject.Helper.Helper;
import com.jsp1.SpringBootProject.Service.RecordDetailsService;
import com.jsp1.SpringBootProject.Service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("*")   // client working on any origin can access
@RequestMapping("/home")
public class RecordController {

//	to store records in database
	 @Autowired
	 private RecordDetailsService recordService;
	 
	 
//	 For database operation
	 @Autowired
	 private RecordDao dao;
	 
	 
//	 for Report--> Generating Excel Sheet
	 @Autowired
	 private ReportService reportService;
	 

	    @PostMapping("/upload")
	    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
	        if (Helper.checkExcelFormat(file)) {
	            //true

	            this.recordService.save(file);

	            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	    }


	    @GetMapping("/getall")
	    public List<RecordDetails> getAllProduct() {
	        return this.recordService.getAllRecords();
	    }
	    
	    
	    @PostMapping("/insert")
		public RecordDetails saveEmployee(@RequestBody RecordDetails record) { // @RequestBody -> when accepting the object
			return dao.insertRecord(record);
		}
	    
	    
	    @GetMapping("/search")
		public RecordDetails searchById(@RequestParam long id) { 		//	@RequestParam -> when accepting the parameter
			return dao.searchById(id);
		}
	    
	    
	    @DeleteMapping("/delete")
		public String deleteEmployee(@RequestParam long id) {
			return dao.deleteRecord(id);
		}
	
	    
	    @PutMapping("/update")
		public RecordDetails updateRecord(@RequestBody RecordDetails record) {
			return dao.updateRecordDetails(record);
		}
	    
	    
	    
	    
	    @GetMapping("/excel")
		public void generateExcelReport(HttpServletResponse response) throws Exception{  // whatever response will generate will store in response object
			
			response.setContentType("application/octet-stream");   // specify the type of response
			
			String headerKey = "Content-Disposition";   		// used to indicate the file should be downloaded 
			String headerValue = "attachment;filename=records.xls";			// file should get attached and downloaded filename will be record.xls

			response.setHeader(headerKey, headerValue);  	// setting the header value to response
			
			reportService.generateExcel(response);		// generating the excel file response
			
			response.flushBuffer();			// It is used to force the response to be written and sent back to the client immediately
		}
	    
	    
}
