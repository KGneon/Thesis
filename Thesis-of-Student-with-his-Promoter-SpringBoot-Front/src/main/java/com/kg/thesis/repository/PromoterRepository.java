package com.kg.thesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kg.thesis.entity.Promoter;

public interface PromoterRepository extends JpaRepository<Promoter, Integer>{
	List<Promoter> findByNumberOfStudentsLeadAsc(Integer numberOfStudentsLead);
}
