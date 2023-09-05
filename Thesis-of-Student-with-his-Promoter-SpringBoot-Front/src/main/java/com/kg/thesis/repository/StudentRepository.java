package com.kg.thesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kg.thesis.entity.Promoter;
import com.kg.thesis.entity.Student;
import com.kg.thesis.entity.Thesis;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	//List<Student> findByStudentName(String studentName);
	List<Student> findByPromoter(Promoter promoter);
	List<Student> findByThesis(Thesis thesis);
	//List<Student> findByThesisIsNull();
}
