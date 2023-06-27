package com.jsp1.SpringBootProject.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp1.SpringBootProject.DTO.RecordDetails;
import com.jsp1.SpringBootProject.Repository.RecordRepo;

@Repository
public class RecordDao {

	@Autowired
	RecordRepo repository;
	
//	Insert an RecordDetails object in database
	public RecordDetails insertRecord(RecordDetails rec) {
		return repository.save(rec);
	}
	
	
//	To find the RecordDetails object based on id .
	public RecordDetails searchById(long id) {
		Optional<RecordDetails> opt =  repository.findById(id); // returns an object of Optional class. Optional class object can store object or null or it can be empty reference variable
		
		if(opt.isPresent()) {	// Checks whether object is present
			return opt.get(); // get method will return an object present in opt object of Optional class
		}
		
		return null;
	}
	
	
//	to delete an RecordDetails from DB
	public String deleteRecord(long id) {
		
		RecordDetails e = searchById(id);		// for checking that object is present or not
		
		if(e!=null) {
			repository.deleteById(id);
			return "Record removed successfully";
		}
		return "Record id not found";
	}
	
	
//	to update an object
	public RecordDetails updateRecordDetails(RecordDetails newRecord) {
		long id = newRecord.getPhoneNumber();
		RecordDetails oldRecord = searchById(id);
		if(oldRecord!=null) {
			return repository.save(newRecord);
		}
		
		return null;
		
	}
	
}
