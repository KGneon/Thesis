package com.kg.thesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kg.thesis.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	List<Student> findByThesisIdIsNull();
}
