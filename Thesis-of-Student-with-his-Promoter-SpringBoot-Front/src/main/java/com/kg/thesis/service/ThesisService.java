package com.kg.thesis.service;

import java.util.List;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.dto.ThesisDTO;
import com.kg.thesis.exception.ThesisException;

public interface ThesisService {
	public List<StudentDTO> getStudents() throws ThesisException;
	public List<ThesisDTO> getTheses() throws ThesisException;
	public List<PromoterDTO> getPromoters() throws ThesisException ;
	public List<StudentDTO> getStudentsWithDoubtfulThesis(Boolean notMatchedOrBlank) throws ThesisException;
	public List<PromoterDTO> getPromotersWithPossibleStudentAllocation() throws ThesisException;
	public List<PromoterDTO> getPromotersBySpecialization(String field);
	public StudentDTO getStudentById(Integer studentId) throws ThesisException;
	public PromoterDTO getPromoterById(Integer promoterId) throws ThesisException;
	public ThesisDTO getThesisById(Integer thesisId) throws ThesisException;
	public void addStudent(StudentDTO studentDTO) throws ThesisException ;
	public void addPromoter(PromoterDTO promoterDTO) throws ThesisException ;
	public void addThesis(ThesisDTO thesisDTO) throws ThesisException;
	public void deleteStudent(Integer studentId) throws ThesisException;
	public void deletePromoter(Integer promoterId) throws ThesisException;
	public void deleteThesis(Integer thesisId) throws ThesisException;
	
	public void allocatePromoterToStudent(Integer studentId, Integer promoterId);
	public void assignThesisToStudent(Integer studentId, Integer thesisId);
	//change thesis
	//change promoter (possibly not need in backend)
	List<PromoterDTO> getPromotersByStudentsLead(Integer studentsLead) throws ThesisException;
}
