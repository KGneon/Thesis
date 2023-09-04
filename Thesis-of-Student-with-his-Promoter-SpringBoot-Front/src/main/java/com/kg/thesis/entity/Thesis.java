package com.kg.thesis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Thesis {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer thesisId;
	private String thesisName;
	private String thesisField;
	private ThesisType thesisType;
	public Integer getThesisId() {
		return thesisId;
	}
	public void setThesisId(Integer thesisId) {
		this.thesisId = thesisId;
	}
	public String getThesisName() {
		return thesisName;
	}
	public void setThesisName(String thesisName) {
		this.thesisName = thesisName;
	}
	public String getThesisField() {
		return thesisField;
	}
	public void setThesisField(String thesisField) {
		this.thesisField = thesisField;
	}
	public ThesisType getThesisType() {
		return thesisType;
	}
	public void setThesisType(ThesisType thesisType) {
		this.thesisType = thesisType;
	}
	
	
}
