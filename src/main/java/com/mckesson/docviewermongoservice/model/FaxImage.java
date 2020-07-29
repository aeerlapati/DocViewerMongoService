package com.mckesson.docviewermongoservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FaxImage")
public class FaxImage {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
    
    private byte[] fax_image;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte[] getFax_image() {
		return fax_image;
	}
	public void setFax_image(byte[] bs) {
		this.fax_image = bs;
	}
	
 
}