package com.kg.thesis.dto;

import com.kg.thesis.entity.Promoter;
import com.kg.thesis.entity.Student;
import com.kg.thesis.entity.Thesis;
import com.kg.thesis.entity.ThesisType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class StudentDTO {
	private Integer studentId;
	@NotNull(message="{student.name.notpresent}")
	@Pattern(regexp="[A-Z][a-z]*(?:[ -][A-Z][a-z]*){0,4}", message="{student.name.invalid}")
	private String studentName;
	@NotNull(message="{student.surname.cannot.be.null}")
	@Pattern(regexp="[A-Z][a-z]*(?:[ -][A-Z][a-z]*){0,4}", message="{student.name.invalid}")
	private String studentSurname;
	private Thesis thesis;
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
	public Thesis getThesis() {
		return thesis;
	}
	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
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
		studentDTO.setThesis(student.getThesis());
		studentDTO.setPromoter(student.getPromoter());
		return studentDTO;
	}
	
	public static Student createEntity(StudentDTO studentDTO) {
		Student student = new Student();
		student.setStudentId(studentDTO.getStudentId());
		student.setStudentName(studentDTO.getStudentName());
		student.setStudentSurname(studentDTO.getStudentSurname());
		student.setThesis(studentDTO.getThesis());
		student.setPromoter(studentDTO.getPromoter());
		return student;
	}
}
