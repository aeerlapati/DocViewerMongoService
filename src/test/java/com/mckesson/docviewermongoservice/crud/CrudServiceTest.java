package com.mckesson.docviewermongoservice.crud;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mckesson.docviewermongoservice.controller.WelcomeController;
import com.mckesson.docviewermongoservice.model.FaxDB;
import com.mckesson.docviewermongoservice.repository.FaxRepository;
import com.mckesson.docviewermongoservice.service.FaxService;

@SpringBootTest
@AutoConfigureMockMvc
public class CrudServiceTest {

	Logger logger = LoggerFactory.getLogger(CrudServiceTest.class);

	@Autowired
	private MockMvc mockMvc ;
	
	@MockBean
	FaxRepository faxRepository;
	
	@Mock
	FaxService faxService;
	
//	@Mock
//	MongoTemplate mongoTemplate;
	
	@Autowired
	FaxDB faxDB;

	Gson gson = new Gson();      
	
    @Test
    public void testCanary() throws Exception {
        mockMvc.perform(get("/canary")).andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void testAddRecordsNoBody() throws Exception {
     	createAddTaskMap();
     	faxDB.setCase_id("");
	  	ObjectMapper mapper = new ObjectMapper();
	  	String sampleJson = mapper.writeValueAsString(faxDB);
        mockMvc.perform(post("/addRecords").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(sampleJson)).andDo(print()).andExpect(status().isBadRequest());
    }
    
    @Test
    public void testAddRecordsservice() throws Exception {
	  	createAddTaskMap();
	  	logger.info(faxDB.getAssigned_user_fname() + "Testing Values");
	  	ObjectMapper mapper = new ObjectMapper();
	  	String sampleJson = mapper.writeValueAsString(faxDB);
	  	logger.info(faxDB.getAssigned_user_fname() + "Testing Values");
	  	String expected = faxDB.toString();
	  	
        //Mockito.when(faxService.saveOrUpdate(Mockito.any(FaxDB.class))).thenReturn(faxDB);
        mockMvc.perform(post("/addRecords").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(sampleJson)).andDo(print()).andExpect(status().isCreated());
    }
    
    @Test
    public void testGetRecordsByUsername() throws Exception {
    	List<FaxDB> retval = new ArrayList<FaxDB>();
    	try {
            mockMvc.perform(get("/getRecordsByUsername").param("username", "!@#$%^&*()(*&^%")).andExpect(status().isNotFound());
        }catch(Exception e) {
        	logger.error(e.getMessage());
        }
    }
     
    private void createAddTaskMap(){
    	  faxDB.setAssigned_user_fname("Robert");
    	  faxDB.setAssigned_user_lname("Downey Jr");
    	  faxDB.setAssigned_username("rdowney");
    	  faxDB.setCase_id("765677");
    	  faxDB.setDocument_type("png");
    	  faxDB.setGroup_id("45454");
    	  faxDB.setIncoming_fax_number("87689");
    	  faxDB.setQueue_id("7387823563");
    	  faxDB.setQueue_status("Pending");
    	  faxDB.setGroup_status("Pending");
    	  faxDB.setProgram_name("cosentyx");
    	  faxDB.setProgram_id("123");
    	  
	}
}