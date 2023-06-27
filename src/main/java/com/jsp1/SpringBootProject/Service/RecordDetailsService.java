package com.jsp1.SpringBootProject.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp1.SpringBootProject.DTO.RecordDetails;
import com.jsp1.SpringBootProject.Helper.Helper;
import com.jsp1.SpringBootProject.Repository.RecordRepo;

@Service  // for buisness logic or functionalities
public class RecordDetailsService {
	
	
//	to store the excel data to database
	
	@Autowired
    private RecordRepo recordRepo;

    public void save(MultipartFile file) {

        try {
            List<RecordDetails> record = Helper.convertExcelToListOfProduct(file.getInputStream());
            this.recordRepo.saveAll(record);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    
//    To get all the records
    
    public List<RecordDetails> getAllRecords() {
        return this.recordRepo.findAll();
    }

}
