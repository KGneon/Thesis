package com.kg.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kg.thesis.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
