package com.mckesson.docviewermongoservice.repository;

import com.mckesson.docviewermongoservice.model.FaxDB;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FaxRepository extends MongoRepository<FaxDB, Integer> {
}