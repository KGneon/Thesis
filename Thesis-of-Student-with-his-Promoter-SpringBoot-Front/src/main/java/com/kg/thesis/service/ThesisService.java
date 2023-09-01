package com.kg.thesis.service;

import java.util.List;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.exception.ThesisException;

public interface ThesisService {
	public List<StudentDTO> getStudents()  throws ThesisException;
	public List<PromoterDTO> getMentors();
	public List<String> getListOfThesis();
	public List<PromoterDTO> getPromoterByStudentsLead(Integer studentsLead);
	public StudentDTO getStudentById(Integer studentId);
	public void addStudent(StudentDTO studentDTO);
	public void addPromoter(PromoterDTO promoterDTO);
	public void updateThesis(String thesis, Integer studentId);
	public void updatePromoter(Integer studentId, Integer promoterId);
	public void deleteThesis(Integer studentId);
	public void deleteStudent(Integer studentId);
	public void deletePromoter(Integer promoterId);
}
