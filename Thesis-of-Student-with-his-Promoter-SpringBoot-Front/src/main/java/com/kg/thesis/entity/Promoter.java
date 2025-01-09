package com.kg.thesis.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promoter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer promoterId;
	private String promoterName;
	private String promoterSurname;
	private String field;
	private Integer numberOfStudentsLead;
	
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
