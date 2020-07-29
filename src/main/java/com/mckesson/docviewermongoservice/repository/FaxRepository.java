package com.mckesson.docviewermongoservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mckesson.docviewermongoservice.model.FaxDB;

public interface FaxRepository extends MongoRepository<FaxDB, String>
{

}