package com.kg.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kg.thesis.entity.Thesis;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Integer>{

}
