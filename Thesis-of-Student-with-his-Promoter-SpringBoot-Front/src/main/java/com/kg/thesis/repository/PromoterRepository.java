package com.kg.thesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kg.thesis.entity.Promoter;

@Repository
public interface PromoterRepository extends JpaRepository<Promoter, Integer>{
	List<Promoter> findByNumberOfStudentsLead(Integer numberOfStudentsLead);
}
