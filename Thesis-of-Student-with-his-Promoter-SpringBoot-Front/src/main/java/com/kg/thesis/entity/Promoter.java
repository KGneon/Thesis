package com.kg.thesis.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Promoter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer promoterId;
	private String promoterName;
	private String promoterSurname;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(field, numberOfStudentsLead, promoterId, promoterName, promoterSurname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promoter other = (Promoter) obj;
		return Objects.equals(field, other.field)
				&& Objects.equals(numberOfStudentsLead, other.numberOfStudentsLead)
				&& Objects.equals(promoterId, other.promoterId) && Objects.equals(promoterName, other.promoterName)
				&& Objects.equals(promoterSurname, other.promoterSurname);
	}
	
	
}
