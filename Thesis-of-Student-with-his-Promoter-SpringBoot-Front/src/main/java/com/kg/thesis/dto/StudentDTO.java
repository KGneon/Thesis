package com.kg.thesis.dto;

import com.kg.thesis.entity.Promoter;
import com.kg.thesis.entity.Student;
import com.kg.thesis.entity.ThesisType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class StudentDTO {
	private Integer studentId;
	@NotNull(message="{student.name.cannot.be.null}")
	@Pattern(regexp="[A-Za-z]+", message="{student.name.invalid}")
	private String studentName;
	@NotNull(message="{student.surname.cannot.be.null}")
	private String studentSurname;
	private String thesisName;
	private String thesisField;
	private ThesisType thesisType;
	private Promoter promoter;
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentSurname() {
		return studentSurname;
	}
	public void setStudentSurname(String studentSurname) {
		this.studentSurname = studentSurname;
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
	public Promoter getPromoter() {
		return promoter;
	}
	public void setPromoter(Promoter promoter) {
		this.promoter = promoter;
	}
	
	public static StudentDTO createDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentId(student.getStudentId());
		studentDTO.setStudentName(student.getStudentName());
		studentDTO.setStudentSurname(student.getStudentSurname());
		studentDTO.setThesisName(student.getThesisName());
		studentDTO.setThesisField(student.getThesisField());
		studentDTO.setThesisType(student.getThesisType());
		studentDTO.setPromoter(student.getPromoter());
		return studentDTO;
	}
	
	public static Student createEntity(StudentDTO studentDTO) {
		Student student = new Student();
		student.setStudentId(studentDTO.getStudentId());
		student.setStudentName(studentDTO.getStudentName());
		student.setStudentSurname(studentDTO.getStudentSurname());
		student.setThesisName(studentDTO.getThesisName());
		student.setThesisField(studentDTO.getThesisField());
		student.setThesisType(studentDTO.getThesisType());
		student.setPromoter(studentDTO.getPromoter());
		return student;
	}
	
}
