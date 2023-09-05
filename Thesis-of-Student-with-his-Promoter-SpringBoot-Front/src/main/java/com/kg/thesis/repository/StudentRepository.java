package com.kg.thesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kg.thesis.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	//List<Student> findByStudentName(String studentName);
	List<Student> findByPromoterId(Integer promoterId);
	List<Student> findByThesisId(Integer thesisId);
	List<Student> findByThesisIdIsNull();
}
