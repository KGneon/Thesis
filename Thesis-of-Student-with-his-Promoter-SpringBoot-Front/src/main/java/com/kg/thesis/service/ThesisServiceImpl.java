package com.kg.thesis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.entity.Promoter;
import com.kg.thesis.entity.Student;
import com.kg.thesis.entity.ThesisType;
import com.kg.thesis.exception.ThesisException;
import com.kg.thesis.repository.PromoterRepository;
import com.kg.thesis.repository.StudentRepository;

@Service(value = "thesisService")
public class ThesisServiceImpl implements ThesisService {

	@Autowired
	PromoterRepository promoterRepository;

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<StudentDTO> getStudents() throws ThesisException {
		List<Student> studentList = studentRepository.findAll();
		if (studentList != null) {
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
	public List<PromoterDTO> getMentors() throws ThesisException {
		List<Promoter> promoterList = promoterRepository.findAll();
		if (promoterList != null) {
			List<PromoterDTO> promoterDTOList = new ArrayList<>();
			promoterList.forEach(p -> {
				PromoterDTO promoterDTO = new PromoterDTO();
				promoterDTO = PromoterDTO.createDTO(p);
				promoterDTOList.add(promoterDTO);

			});
			return promoterDTOList;
		} else {
			throw new ThesisException("Service.Service.NO_PROMOTER_AVAILABLE");
		}
	}

	@Override
	public List<String> getListOfThesis() throws ThesisException {
		List<Student> studentList = studentRepository.findAll();
		List<String> thesisList = new ArrayList<>();
		if (!studentList.isEmpty()) {
			studentList.forEach(s -> {
				if (s.getThesisName() != null) {
					thesisList.add(s.getThesisName());
				}
			});
		} else {
			throw new ThesisException("Service.EMPTY_STUDENTS_LIST");
		}
		
		if(thesisList.isEmpty()) throw new ThesisException("Service.NO_THESIS");
		else return thesisList; 
	}

	@Override
	//UNFINISHED!!!
	public List<PromoterDTO> getPromoterByStudentsLead(Integer studentsLead) throws ThesisException {
		List<Promoter> promoterList = promoterRepository.findAll();
		List<PromoterDTO> promoterDTOListByLeadsNumber = new ArrayList<>();
		promoterList.forEach(p -> {
			if(p.getNumberOfStudentsLead() < 5) {
				PromoterDTO promoterDTO = new PromoterDTO();
				promoterDTO = PromoterDTO.createDTO(p);
				promoterDTOListByLeadsNumber.add(promoterDTO);
			}
			//else throw new ThesisException("Service.TO_MANY_LEADS");		
		});
		return promoterDTOListByLeadsNumber;
	}

	@Override
	public StudentDTO getStudentById(Integer studentId) throws ThesisException {
		Optional<Student> optionalStudent= studentRepository.findById(studentId);
		Student student = optionalStudent.orElseThrow(() -> new ThesisException("Service.NO_STUDENT_BY_ID"));
		StudentDTO studentDTO = StudentDTO.createDTO(student);
		return studentDTO;
	}

	@Override
	//UNFINISHED validation of lack of promoter!!!
	public void addStudent(StudentDTO studentDTO) throws ThesisException {
		if(studentDTO != null) {
			
			Student student = StudentDTO.createEntity(studentDTO);
			Optional<Promoter> optionalPromoter = promoterRepository.findById(student.getPromoter().getPromoterId());
			Promoter promoter = optionalPromoter.orElseThrow(() -> new ThesisException("Service.NO_PROMOTER_BY_ID"));
			promoter.setNumberOfStudentsLead(promoter.getNumberOfStudentsLead() + 1);
			promoterRepository.save(promoter);
			studentRepository.save(student);
		} else {
			//maybe not needed????
			throw new ThesisException("Service.NO_DATA");
		}
		
	}

	@Override
	//UNFINISHED!!!
	public void addPromoter(PromoterDTO promoterDTO) throws ThesisException {
		if(promoterDTO != null) {
			Promoter promoter = PromoterDTO.createEntity(promoterDTO);
			promoterRepository.save(promoter);
		} else {
			//...
		}

	}

	@Override
	//TO CHANGE WITH THESIS ENTITY
	//UNFINISHED input validation
	public void updateThesis(String thesis, Integer studentId) throws ThesisException {
		Optional<Student> optionalStudent = studentRepository.findById(studentId);
		Student student = optionalStudent.orElseThrow(() -> new ThesisException("Service.NO_STUDENT_BY_ID"));
		student.setThesisName(thesis);
		studentRepository.save(student);
	}

	
//	TO ADD WITH THESIS ENTITY	
//	@Override
//	public void deleteThesis(Integer studentId) throws ThesisException {
//		Optional<Student> optionalStudent = studentRepository.findById(studentId);
//		Student student = optionalStudent.orElseThrow(() -> new ThesisException("Service.NO_STUDENT_BY_ID"));
//		student.setThesisName(thesis);
//	}

	@Override
	public void deleteStudent(Integer studentId) throws ThesisException {
		Optional<Student> optionalStudent = studentRepository.findById(studentId);
		Student student = optionalStudent.orElseThrow(() -> new ThesisException("Service.NO_STUDENT_BY_ID"));
		studentRepository.delete(student);
	}

	@Override
	public void deletePromoter(Integer promoterId) throws ThesisException {
		Optional<Promoter> optionalPromoter = promoterRepository.findById(promoterId);
		Promoter promoter = optionalPromoter.orElseThrow(() -> new ThesisException("Service.NO_PROMOTER_BY_ID"));
		promoterRepository.delete(promoter);
	}

}
