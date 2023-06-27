package com.jsp1.SpringBootProject.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="RECORD_DETAILS")
public class RecordDetails {
	
	private String name;
	
	@Id
	private long phoneNumber;
	
	
	
	
//	Parameterised Constructor
	public RecordDetails(String name, long phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

//	Non parameterised Constructor
	public RecordDetails() {
	
	}

//  Getter and Setters
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
