package com.kg.thesis.dto;

import com.kg.thesis.entity.Thesis;
import com.kg.thesis.entity.ThesisType;

public class ThesisDTO {
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
	
	public static Thesis createEntity(ThesisDTO thesisDTO) {
		Thesis thesis = new Thesis();
		//thesis.setThesisId(thesisDTO.getThesisId());
		thesis.setThesisName(thesisDTO.getThesisName());
		thesis.setThesisField(thesisDTO.getThesisField());
		thesis.setThesisType(thesisDTO.getThesisType());
		return thesis;
	}
	
	public static ThesisDTO createDTO(Thesis thesis) {
		ThesisDTO thesisDTO = new ThesisDTO();
		thesisDTO.setThesisId(thesis.getThesisId());
		thesisDTO.setThesisName(thesis.getThesisName());
		thesisDTO.setThesisField(thesis.getThesisField());
		thesisDTO.setThesisType(thesis.getThesisType());
		return thesisDTO;
	}
}
