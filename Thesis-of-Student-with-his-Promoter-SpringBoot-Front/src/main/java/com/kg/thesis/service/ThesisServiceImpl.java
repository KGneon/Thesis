package com.kg.thesis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.dto.ThesisDTO;
import com.kg.thesis.entity.Promoter;
import com.kg.thesis.entity.Student;
import com.kg.thesis.entity.Thesis;
import com.kg.thesis.exception.ThesisException;
import com.kg.thesis.repository.PromoterRepository;
import com.kg.thesis.repository.StudentRepository;
import com.kg.thesis.repository.ThesisRepository;

@Service(value = "thesisService")
public class ThesisServiceImpl implements ThesisService {

	@Autowired
	PromoterRepository promoterRepository;

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ThesisRepository thesisRepository;

	@Override
	public List<StudentDTO> getStudents() throws ThesisException {
		List<Student> studentList = studentRepository.findAll();
		if (studentList != null) {
			List<StudentDTO> studentDTOList = new ArrayList<>();
			studentList.forEach(s -> {
				StudentDTO studentDTO = StudentDTO.createDTO(s);
				studentDTOList.add(studentDTO);
			});
			return studentDTOList;
		} else {
			throw new ThesisException("Service.EMPTY_STUDENTS_LIST");
		}
	}
	
	@Override
	public List<StudentDTO> getStudentsWithDoubtfulThesis(Boolean notMatchedOrBlank) throws ThesisException {
		List<Student> studentList = studentRepository.findAll();
		if(!studentList.isEmpty()) {
			List<StudentDTO> studentDTOList = new ArrayList<>();
			studentList.forEach(s -> {
				if(notMatchedOrBlank == true && s.getPromoter().getField() != s.getThesis().getThesisField()) {
					StudentDTO studentDTO = StudentDTO.createDTO(s);
					studentDTOList.add(studentDTO);
				}
				if(notMatchedOrBlank == false && s.getThesis() == null) {
					StudentDTO studentDTO = StudentDTO.createDTO(s);
					studentDTOList.add(studentDTO);
				}
			});
			
			if(studentDTOList.isEmpty()) throw new ThesisException("Service.NO_DOUBTFUL_THESIS");
			else return studentDTOList;
			
		} else {
			throw new ThesisException("Service.EMPTY_STUDENTS_LIST");
		}
	}

	@Override
	public List<PromoterDTO> getPromoters() throws ThesisException {
		List<Promoter> promoterList = promoterRepository.findAll();
		if (promoterList != null) {
			List<PromoterDTO> promoterDTOList = new ArrayList<>();
			promoterList.forEach(p -> {
				PromoterDTO promoterDTO = PromoterDTO.createDTO(p);
				promoterDTOList.add(promoterDTO);
			});
			return promoterDTOList;
		} else {
			throw new ThesisException("Service.Service.NO_PROMOTER_AVAILABLE");
		}
	}

	@Override
	public List<PromoterDTO> getPromotersWithPossibleStudentAllocation() throws ThesisException {
		List<Promoter> promoterList = promoterRepository.findAll();
		List<PromoterDTO> promoterDTOList = new ArrayList<>();
		promoterList.forEach(p -> {
			if(p.getNumberOfStudentsLead() < 5) {
				PromoterDTO promoterDTO = PromoterDTO.createDTO(p);
				promoterDTOList.add(promoterDTO);
			}	
		});
		return promoterDTOList;
	}
	
	@Override
	public List<PromoterDTO> getPromotersByStudentsLead(Integer studentsLead) throws ThesisException {
		List<Promoter> promoterList = promoterRepository.findByNumberOfStudentsLeadAsc(studentsLead);
		if(promoterList.isEmpty()) throw new ThesisException("Service.NO_PROMOTERS_WITH_THAT_LEAD");
		else {
			List<PromoterDTO> promoterDTOList = new ArrayList<>(); 
			promoterList.forEach(p -> {
				PromoterDTO promoterDTO = PromoterDTO.createDTO(p);
				promoterDTOList.add(promoterDTO);
				
			});
			return promoterDTOList;
		}
	}
	
	@Override
	public List<ThesisDTO> getThesis() throws ThesisException {
		List<Thesis> thesisList = thesisRepository.findAll();
		if (thesisList != null) {
			List<ThesisDTO> thesisDTOList = new ArrayList<>();
			thesisList.forEach(p -> {
				ThesisDTO thesisDTO = ThesisDTO.createDTO(p);
				thesisDTOList.add(thesisDTO);
			});
			return thesisDTOList;
		} else {
			throw new ThesisException("Service.Service.NO_THESIS");
		}
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
	public void addThesis(ThesisDTO thesisDTO) throws ThesisException {
		// TODO Auto-generated method stub
		
	}

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

	@Override
	public void updateThesis(String thesis, Integer studentId) throws ThesisException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteThesis(Integer thesisId) throws ThesisException {
		// TODO Auto-generated method stub
		
	}

}
