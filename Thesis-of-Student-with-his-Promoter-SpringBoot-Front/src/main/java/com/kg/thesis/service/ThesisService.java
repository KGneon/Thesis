package com.kg.thesis.service;

import java.util.List;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.dto.ThesisDTO;
import com.kg.thesis.exception.ThesisException;

public interface ThesisService {
	//GET ALL
	public List<StudentDTO> getStudents() throws ThesisException;
	public List<PromoterDTO> getPromoters() throws ThesisException;
	public List<ThesisDTO> getTheses() throws ThesisException;
	//POST (ADD)
	public void addStudent(StudentDTO studentDTO) throws ThesisException ;
	public void addPromoter(PromoterDTO promoterDTO) throws ThesisException ;
	public void addThesis(ThesisDTO thesisDTO) throws ThesisException;
	//DELETE
	public void deleteStudent(Integer studentId) throws ThesisException;
	public void deletePromoter(Integer promoterId) throws ThesisException;
	public void deleteThesis(Integer thesisId) throws ThesisException;
	//GET BY ID
	public StudentDTO getStudentById(Integer studentId) throws ThesisException;
	public PromoterDTO getPromoterById(Integer promoterId) throws ThesisException;
	public ThesisDTO getThesisById(Integer thesisId) throws ThesisException;
	//ALLOCATION AND ASSIGNEMENT
	public void allocatePromoterToStudent(Integer studentId, Integer promoterId) throws ThesisException;
	public void assignThesisToStudent(Integer studentId, Integer thesisId) throws ThesisException;
	//SPECIFIC METHODS
	//???FRONTEND OR BACKEND???
	public List<>
	public List<StudentDTO> getStudentsWithDoubtfulThesis(Boolean notMatchedOrBlank) throws ThesisException;
	public List<PromoterDTO> getPromotersWithPossibleStudentAllocation() throws ThesisException;
	public List<PromoterDTO> getPromotersByStudentsLead(Integer studentsLead) throws ThesisException;
	public List<PromoterDTO> getPromotersBySpecialization(String field)  throws ThesisException;
}
