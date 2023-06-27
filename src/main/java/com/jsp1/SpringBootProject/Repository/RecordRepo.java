package com.jsp1.SpringBootProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp1.SpringBootProject.DTO.RecordDetails;

public interface RecordRepo extends JpaRepository<RecordDetails, Long>{

}
