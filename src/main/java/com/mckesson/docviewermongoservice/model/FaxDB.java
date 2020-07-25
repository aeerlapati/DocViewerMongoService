package com.mckesson.docviewermongoservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FaxDB")
public class FaxDB {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
	private String queue_id;
    private String document_id;
    private String group_id;
    private String case_id;
    private String queue_status;
    private String create_Date;
    private String assigned_username;
    private String assigned_user_fname;
    private String assigned_user_lname;
    private String document_type;
    private String incoming_fax_number;
    private String group_status;
    
    
	public String getDocument_id() {
		return document_id;
	}
	public void setDocument_id(String document_id) {
		this.document_id = document_id;
	}
	public String getQueue_id() {
		return queue_id;
	}
	public void setQueue_id(String queue_id) {
		this.queue_id = queue_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getCase_id() {
		return case_id;
	}
	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}
	public String getQueue_status() {
		return queue_status;
	}
	public void setQueue_status(String queue_status) {
		this.queue_status = queue_status;
	}
	public String getCreate_Date() {
		return create_Date;
	}
	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}
	public String getAssigned_username() {
		return assigned_username;
	}
	public void setAssigned_username(String assigned_username) {
		this.assigned_username = assigned_username;
	}
	public String getAssigned_user_fname() {
		return assigned_user_fname;
	}
	public void setAssigned_user_fname(String assigned_user_fname) {
		this.assigned_user_fname = assigned_user_fname;
	}
	public String getAssigned_user_lname() {
		return assigned_user_lname;
	}
	public void setAssigned_user_lname(String assigned_user_lname) {
		this.assigned_user_lname = assigned_user_lname;
	}
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	public String getIncoming_fax_number() {
		return incoming_fax_number;
	}
	public void setIncoming_fax_number(String incoming_fax_number) {
		this.incoming_fax_number = incoming_fax_number;
	}
	public String getGroup_status() {
		return group_status;
	}
	public void setGroup_status(String group_status) {
		this.group_status = group_status;
	}
 
}