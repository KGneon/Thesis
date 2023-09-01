package com.kg.thesis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.entity.Student;
import com.kg.thesis.exception.ThesisException;
import com.kg.thesis.repository.PromoterRepository;
import com.kg.thesis.repository.StudentRepository;

@Service(value="thesisService")
public class ThesisServiceImpl implements ThesisService {

	@Autowired
	PromoterRepository promoterRepository;
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<StudentDTO> getStudents() throws ThesisException {
		List<Student> studentList = studentRepository.findAll();
		if(studentList != null) {
			List<StudentDTO> studentDTOList = new ArrayList<>();
			studentList.forEach(s -> {
						StudentDTO studentDTO = new StudentDTO();
						studentDTO = StudentDTO.createDTO(s);
						studentDTOList.add(studentDTO);
					});
			return studentDTOList;
		} else {
			throw new ThesisException("Service.EMPTY_STUDENTS_LIST");
		}
	}

	@Override
	public List<PromoterDTO> getMentors() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<String> getListOfThesis() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromoterDTO> getPromoterByStudentsLead(Integer studentsLead) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO getStudentById(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStudent(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPromoter(PromoterDTO promoterDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateThesis(String thesis, Integer studentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePromoter(Integer studentId, Integer promoterId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteThesis(Integer studentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudent(Integer studentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePromoter(Integer promoterId) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
