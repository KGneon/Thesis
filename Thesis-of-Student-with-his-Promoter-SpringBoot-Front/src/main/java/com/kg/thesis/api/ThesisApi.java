package com.kg.thesis.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.dto.ThesisDTO;
import com.kg.thesis.entity.Promoter;
import com.kg.thesis.entity.Student;
import com.kg.thesis.exception.ThesisException;
import com.kg.thesis.service.ThesisService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
@Validated
public class ThesisApi {
	
	@Autowired
	ThesisService thesisService;
	
	@Autowired
	Environment environment;

	//GET ALL
	@GetMapping(value="/students")
	public ResponseEntity<List<StudentDTO>> getAllStudents() throws ThesisException{
		List<StudentDTO> listOfStudents = thesisService.getStudents();
		return new ResponseEntity<>(listOfStudents, HttpStatus.OK);
	}
	@GetMapping(value="/promoters")
	public ResponseEntity<List<PromoterDTO>> getAllPromoters() throws ThesisException{
		List<PromoterDTO> listOfPromoters = thesisService.getPromoters();
		return new ResponseEntity<>(listOfPromoters, HttpStatus.OK);
	}
	@GetMapping(value="/theses")
	public ResponseEntity<List<ThesisDTO>> getAllTheses() throws ThesisException{
		List<ThesisDTO> listOfTheses = thesisService.getTheses();
		return new ResponseEntity<>(listOfTheses, HttpStatus.OK);
	}
	//POST (ADD)
	@PostMapping(value="/students/add")
	public ResponseEntity<String> addStudent(@RequestBody StudentDTO studentDTO) throws ThesisException{
		thesisService.addStudent(studentDTO);
		String message = "API.STUDENT_ADDED";
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	@PostMapping(value="/promoters/add")
	public ResponseEntity<String> addPromoter(@RequestBody PromoterDTO promoterDTO) throws ThesisException{
		thesisService.addPromoter(promoterDTO);
		String message = "API.PROMOTER_ADDED";
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	@PostMapping(value="/theses/add")
	public ResponseEntity<String> addThesis(@RequestBody ThesisDTO thesisDTO) throws ThesisException{
		thesisService.addThesis(thesisDTO);
		String message = "API.THESIS_ADDED";
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	//UPDATE
	//do ogarniecia
	@PutMapping(value="/thesis/update")
	public ResponseEntity<String> updateThesisOfStudent(@RequestBody ThesisDTO thesisDTO) throws ThesisException{
		//thesisService.addThesis(thesisDTO);
		String message = "";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	//do ogarniecia
	@PutMapping(value="/promoter/{studentId}/{promoterId}")
	public ResponseEntity<String> updatePromoterOfStudent(@PathVariable Integer studentId, @PathVariable Integer promoterId) throws ThesisException{

		String message = "";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	//DELETE
	@DeleteMapping(value="/students/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer studentId) throws ThesisException{
		thesisService.deleteStudent(studentId);
		String message = "API.STUDENT_DELETED";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	@DeleteMapping(value="/promoters/{promoterId}")
	public ResponseEntity<String> deletePromoter(@PathVariable Integer promoterId) throws ThesisException{
		thesisService.deletePromoter(promoterId);
		String message = "API.PROMOTER_DELETED";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	@DeleteMapping(value="/thesis/{thesisId}")
	public ResponseEntity<String> deleteThesis(@PathVariable Integer thesisId) throws ThesisException{
		thesisService.deleteThesis(thesisId);
		String message = "API.THESIS_DELETED";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	//GET ONE
	@GetMapping(value="/students/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Integer studentId) throws ThesisException{
		StudentDTO studentDTO = thesisService.getStudentById(studentId);
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}

	//OTHER
	@GetMapping(value="/students/{matched}")
	public ResponseEntity<List<StudentDTO>> getStudentsWithDoubtfulThesis(@PathVariable("matched") Boolean notMatchedOrBlank)  throws ThesisException{
		List<StudentDTO> listOfStudents = thesisService.getStudentsWithDoubtfulThesis(notMatchedOrBlank);
		return new ResponseEntity<>(listOfStudents, HttpStatus.OK);
	}
	//OGARNĄĆ
	@GetMapping(value="/promoters/possible_allocation")
	public ResponseEntity<List<PromoterDTO>> getPromotersWithPossibleStudentAllocation() throws ThesisException{
		List<PromoterDTO> listOfPromoters = thesisService.getPromotersWithPossibleStudentAllocation();
		return new ResponseEntity<>(listOfPromoters, HttpStatus.OK);
	}
	//nie ogarnia wartosci na minusie
	@GetMapping(value="/promoters/{number}")
	public ResponseEntity<List<PromoterDTO>> getPromotersByNumberOfStudentsLead(@PathVariable("number") Integer studentsLead) throws ThesisException{
		List<PromoterDTO> listOfPromoters = thesisService.getPromotersByStudentsLead(studentsLead);
		return new ResponseEntity<>(listOfPromoters, HttpStatus.OK);
	}
	//need to add number for unassingning student and its thesis and promoters like numberOfStudentsLead
	//need to add ids names and other details to messages
	

	

}
