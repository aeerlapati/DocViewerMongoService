package com.mckesson.docviewermongoservice.service;

import java.util.List;
import java.util.Optional;

import com.mckesson.docviewermongoservice.model.FaxDB;

/**
 * Created by jt on 1/10/17.
 */
public interface FaxService {

    List<FaxDB> listAll();

    Optional<FaxDB> getById(String id);

    FaxDB saveOrUpdate(FaxDB faxDB);

    void delete(FaxDB faxDB);

}