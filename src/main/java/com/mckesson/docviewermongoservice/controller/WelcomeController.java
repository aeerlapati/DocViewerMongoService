package com.mckesson.docviewermongoservice.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mckesson.docviewermongoservice.exception.InvalidInputException;
import com.mckesson.docviewermongoservice.model.FaxDB;
import com.mckesson.docviewermongoservice.repository.FaxRepository;
import com.mckesson.docviewermongoservice.response.AddRecordsRequest;
import com.mckesson.docviewermongoservice.service.FaxService;
import com.mckesson.docviewermongoservice.util.IsEmptyOrNull;

@RestController
public class WelcomeController {

	Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@Autowired
	FaxService faxService;

	@Autowired
	FaxRepository faxRepository;
	
	@Autowired
	FaxDB faxDB;
	
	public class Validator {
	       public boolean validFlag;
	       List<String> missingParams;
	}
	
	IsEmptyOrNull isEmptyOrNull = new IsEmptyOrNull();
		
	@GetMapping("/canary")
	public String checkCanary() {
		return "Tweet";
	}

	@PostMapping("/addRecords")
	public ResponseEntity<FaxDB> addRecords(@Valid @RequestBody AddRecordsRequest faxRequest) throws URISyntaxException {
		
		Validator res = ValidateRequest(faxRequest);
		if(res.validFlag == true) {
			String missingparams = null;
			for(int i = 0; i < res.missingParams.size();i++) {
				if(missingparams == null) {
					missingparams = res.missingParams.get(i);
				}else {
					missingparams = missingparams.concat("," + " " + res.missingParams.get(i));
				}
			}
			throw new InvalidInputException(" Invalid Request, following paramerts are missing : "+ missingparams);

		}

		logger.info("Inside the Add Records Service" + faxRequest.getAssigned_user_fname());
		if(faxRequest.getCase_id() != "") {
			try {
				faxDB = setFaxData(faxRequest);
			}catch(Exception e) {
				logger.error(e.getMessage());
			}
			logger.info("Inside if faxrequest check");
			faxService.saveOrUpdate(faxDB);
			logger.info(" Response Data  :"+faxDB);
			return new ResponseEntity<>(faxDB, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(new FaxDB(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getRecordsByUsername")
	public ResponseEntity<List<FaxDB>> getRecordByUname(@RequestParam("username") String username) {
		logger.error("Inside getRecordsByUsername Service" + username);
		List<FaxDB> returnVal = new ArrayList<FaxDB>();
		if(!isEmptyOrNull.checkValue(username)) {
			faxDB.setAssigned_username(username);
			faxDB.setCreatedDate(null);

			try {
				returnVal = faxService.getDataByExample(faxDB);

				if(returnVal != null && returnVal.size() > 0) {
					logger.info(returnVal.get(0).toString());
					return new ResponseEntity<>(returnVal, HttpStatus.FOUND);
				}else {
					return new ResponseEntity<>(returnVal, HttpStatus.NOT_FOUND);
				}
			}catch(Exception e) {
				logger.error(e.getMessage());
				logger.info(e.getMessage());
				logger.info(e.getStackTrace().toString());

				return new ResponseEntity<>(returnVal, HttpStatus.EXPECTATION_FAILED); 
			}

		}else {
			return new ResponseEntity<>(returnVal, HttpStatus.BAD_REQUEST);
		}

	}


	private FaxDB setFaxData(AddRecordsRequest faxRequest) throws IOException {
		FaxDB faxData = new FaxDB();
		faxData.setAssigned_user_fname(faxRequest.getAssigned_user_fname());
		faxData.setAssigned_user_lname(faxRequest.getAssigned_user_lname());
		faxData.setAssigned_username(faxRequest.getAssigned_username());
		faxData.setCase_id(faxRequest.getCase_id());
		faxData.setDocument_id(faxRequest.getDocument_id());
		faxData.setDocument_type(faxRequest.getDocument_type());
		faxData.setGroup_id(faxRequest.getGroup_id());
		faxData.setGroup_status(faxRequest.getGroup_status());
		faxData.setIncoming_fax_number(faxRequest.getIncoming_fax_number());
		faxData.setQueue_id(faxRequest.getQueue_id());
		faxData.setQueue_status(faxRequest.getQueue_status());
		faxData.setProgram_name(faxRequest.getProgram_name());
		faxData.setProgram_id(faxRequest.getProgram_id());
		return faxData;
	}
	
	
	private Validator ValidateRequest(AddRecordsRequest faxRequest) {
		boolean retFlag = false;
		List<String> errorParams = new ArrayList<String>();
		
		Validator v = new Validator();
		
		if(isEmptyOrNull.checkValue(faxRequest.getAssigned_user_fname())){
			errorParams.add("assigned_user_fname");
			retFlag = true;
		}
		if(isEmptyOrNull.checkValue(faxRequest.getAssigned_user_lname())) {
			errorParams.add("assigned_user_lname");
			retFlag = true;
		}
		if(isEmptyOrNull.checkValue(faxRequest.getAssigned_username())) {
			errorParams.add("assigned_username");
			retFlag = true;
		}
		if(isEmptyOrNull.checkValue(faxRequest.getCase_id())) {
			errorParams.add("case_id");
			retFlag = true;
		}
		if(isEmptyOrNull.checkValue(faxRequest.getDocument_type())) {
			errorParams.add("document_type");
			retFlag = true;
		}
		if(isEmptyOrNull.checkValue(faxRequest.getGroup_id())) {
			errorParams.add("group_id");
			retFlag = true;
		}
		if(isEmptyOrNull.checkValue(faxRequest.getGroup_status())) {
			errorParams.add("group_status");
			retFlag = true;
		}
		if(isEmptyOrNull.checkValue(faxRequest.getProgram_name())) {
			errorParams.add("program_name");
			retFlag = true;
		}
		if(isEmptyOrNull.checkValue(faxRequest.getProgram_id())) {
			errorParams.add("program_id");
			retFlag = true;
		} 
		if(isEmptyOrNull.checkValue(faxRequest.getIncoming_fax_number())) {
			errorParams.add("incoming_fax_number");
			retFlag = true;
		}
		if(isEmptyOrNull.checkValue(faxRequest.getQueue_id())) {
			errorParams.add("queue_id");
			retFlag = true;
		}
		v.missingParams = errorParams;
		v.validFlag = retFlag;
		return v;
		
	}

}