package com.kg.thesis.dto;

import com.kg.thesis.entity.ThesisType;

public class ThesisDTO {
	private Integer thesisId;
	private Integer thesisName;
	private String thesisField;
	private ThesisType thesisType;
	
	public Integer getThesisId() {
		return thesisId;
	}
	public void setThesisId(Integer thesisId) {
		this.thesisId = thesisId;
	}
	public Integer getThesisName() {
		return thesisName;
	}
	public void setThesisName(Integer thesisName) {
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
