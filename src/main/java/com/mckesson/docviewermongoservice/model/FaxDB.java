package com.mckesson.docviewermongoservice.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class FaxDB {

    @Id
    private String IncomingFaxID;
    private String ProgramName;
    private String DID;
    private String GroupID;
    private String ImageID;
    private String Classification ;
    private String Blob;

	public String getIncomingFaxID() {
		return this.IncomingFaxID;
	}

	public void setIncomingFaxID(String IncomingFaxID) {
		this.IncomingFaxID = IncomingFaxID;
	}

	public String getProgramName() {
		return this.ProgramName;
	}

	public void setProgramName(String ProgramName) {
		this.ProgramName = ProgramName;
	}

	public String getDID() {
		return this.DID;
	}

	public void setDID(String DID) {
		this.DID = DID;
	}

	public String getGroupID() {
		return this.GroupID;
	}

	public void setGroupID(String GroupID) {
		this.GroupID = GroupID;
	}

	public String getImageID() {
		return this.ImageID;
	}

	public void setImageID(String ImageID) {
		this.ImageID = ImageID;
	}

	public String getClassification() {
		return this.Classification;
	}

	public void setClassification(String Classification) {
		this.Classification = Classification;
	}

	public String getBlob() {
		return Blob;
	}

	public void setBlob(String blob) {
		Blob = blob;
	}

}