package com.kg.thesis.dto;

import com.kg.thesis.entity.Promoter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PromoterDTO {
	private Integer promoterId;
	@NotNull(message="{promoter.name.notpresent}")
	@Pattern(regexp="[A-Z][a-z]*(?:[ -][A-Z][a-z]*){0,4}", message="{promoter.name.invalid}")
	private String promoterName;
	@NotNull(message="{promoter.surname.notpresent}")
	@Pattern(regexp="[A-Z][a-z]*(?:[ -][A-Z][a-z]*){0,4}", message="{promoter.surname.invalid}")
	private String promoterSurname;
	@NotNull(message="{promoter.field.notpresent}")
	private String field;
	private Integer numberOfStudentsLead;
	
	public Integer getPromoterId() {
		return promoterId;
	}
	public void setPromoterId(Integer promoterId) {
		this.promoterId = promoterId;
	}
	public String getPromoterName() {
		return promoterName;
	}
	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}
	public String getPromoterSurname() {
		return promoterSurname;
	}
	public void setPromoterSurname(String promoterSurname) {
		this.promoterSurname = promoterSurname;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Integer getNumberOfStudentsLead() {
		return numberOfStudentsLead;
	}
	public void setNumberOfStudentsLead(Integer numberOfStudentsLead) {
		this.numberOfStudentsLead = numberOfStudentsLead;
	}
	
	public static PromoterDTO createDTO(Promoter promoter) {
		PromoterDTO promoterDTO = new PromoterDTO();
		promoterDTO.setPromoterId(promoter.getPromoterId());
		promoterDTO.setPromoterName(promoter.getPromoterName());
		promoterDTO.setPromoterSurname(promoter.getPromoterSurname());
		promoterDTO.setField(promoter.getField());
		promoterDTO.setNumberOfStudentsLead(promoter.getNumberOfStudentsLead());
		return promoterDTO;
	}
	
	public static Promoter createEntity(PromoterDTO promoterDTO) {
		Promoter promoter = new Promoter();
		promoter.setPromoterId(promoterDTO.getPromoterId());
		promoter.setPromoterName(promoterDTO.getPromoterName());
		promoter.setPromoterSurname(promoterDTO.getPromoterSurname());
		promoter.setField(promoterDTO.getField());
		promoter.setNumberOfStudentsLead(promoterDTO.getNumberOfStudentsLead());
		return promoter;
	}
}
