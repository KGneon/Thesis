package com.kg.thesis.service;

import java.util.List;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.exception.ThesisException;

public interface ThesisService {
	public List<StudentDTO> getStudents() throws ThesisException;
	public List<PromoterDTO> getMentors() throws ThesisException ;
	public List<String> getListOfThesis() throws ThesisException ;
	public List<PromoterDTO> getPromoterByStudentsLead(Integer studentsLead) throws ThesisException ;
	public StudentDTO getStudentById(Integer studentId) throws ThesisException ;
	public void addStudent(StudentDTO studentDTO) throws ThesisException ;
	public void addPromoter(PromoterDTO promoterDTO) throws ThesisException ;
	public void updateThesis(String thesis, Integer studentId) throws ThesisException ;
	//public void deleteThesis(Integer studentId) throws ThesisException ;
	public void deleteStudent(Integer studentId) throws ThesisException ;
	public void deletePromoter(Integer promoterId) throws ThesisException ;
}
