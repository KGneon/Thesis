package com.kg.thesis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thesis {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long thesisId;
	private String thesisName;
	private String thesisField;
	@Enumerated(EnumType.STRING)
	private ThesisType thesisType;
	private String summary;
	private String status;
	private String bookmark;
//	@ManyToOne
//	@JoinColumn(name = "student_student_id")
//	private Student student;
//	@ManyToOne
//	@JoinColumn(name = "promoter_promoter_id")
//	private Promoter promoter;
}
