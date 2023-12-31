package com.kg.thesis.entity;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer studentId;
	private String studentName;
	private String studentSurname;
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="thesis_id")
	private Thesis thesis;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="promoter_id")
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
	@Override
	public int hashCode() {
		return Objects.hash(promoter, studentId, studentName, studentSurname, thesis);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(promoter, other.promoter) && Objects.equals(studentId, other.studentId)
				&& Objects.equals(studentName, other.studentName)
				&& Objects.equals(studentSurname, other.studentSurname)
				&& Objects.equals(thesis, other.thesis);
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentSurname=" + studentSurname
				+ ", thesisName=" + thesis + ", promoter=" + promoter + "]";
	}
	
	
}
