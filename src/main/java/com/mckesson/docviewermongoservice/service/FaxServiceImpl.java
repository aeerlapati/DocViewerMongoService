package com.mckesson.docviewermongoservice.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mckesson.docviewermongoservice.model.FaxDB;
import com.mckesson.docviewermongoservice.repository.FaxRepository;

/**
 * Created by Abhinav on 07/20.
 */
@Service
public class FaxServiceImpl implements FaxService {

	Logger logger = LoggerFactory.getLogger(FaxServiceImpl.class);
	
	@Autowired
    FaxRepository faxRepository;
    
	@Override
	public List<FaxDB> listAll() {
		return faxRepository.findAll();
	}

	@Override
	public Optional<FaxDB> getById(String id) {
		return faxRepository.findById(id);
	}

	@Override
	public FaxDB saveOrUpdate(FaxDB faxDB) {
		logger.info("faxdb value:" + faxDB.toString());
		return faxRepository.save(faxDB);
	}

	@Override
	public void delete(FaxDB faxDB) {
		faxRepository.delete(faxDB);
	}
	
}
