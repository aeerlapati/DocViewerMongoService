package com.mckesson.docviewermongoservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mckesson.docviewermongoservice.model.FaxDB;
import com.mckesson.docviewermongoservice.repository.FaxRepository;
import com.mckesson.docviewermongoservice.service.FaxService;

@RestController
public class WelcomeController {
	
	Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@Autowired
	FaxService faxService;
	 
	
    @GetMapping("/canary")
    public String checkCanary() {
        return "Tweet";
    }
    
    @PostMapping("/addRecords")
    public ResponseEntity<FaxDB> addRecords(@RequestBody FaxDB faxRequest) {
		logger.info("Inside the Add Records Service");
		if(faxRequest.getCase_id() != "") {
			logger.info("Inside if faxrequest check");
			FaxDB faxDetail = faxService.saveOrUpdate(faxRequest);
			logger.info(" Response Data  :"+faxDetail);
			return new ResponseEntity<>(faxDetail, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(new FaxDB(), HttpStatus.BAD_REQUEST);
		}
    }
}