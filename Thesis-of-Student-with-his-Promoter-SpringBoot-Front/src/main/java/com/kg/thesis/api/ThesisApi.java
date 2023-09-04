package com.kg.thesis.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.kg.thesis.service.ThesisService;

@RestController
@RequestMapping(value="/thesis")
@Validated
public class ThesisApi {
	
	@Autowired
	ThesisService thesisService;
	
	@Autowired
	Environment environment;
		
	@GetMapping(value="/students")
	public ResponseEntity<List<StudentDTO>> getAllStudents(){
		List<StudentDTO> listOfStudents = new ArrayList<>();
		
		
		return new ResponseEntity<>(listOfStudents, HttpStatus.OK);
	}
	
	@GetMapping(value="/students/badthesis")
	public ResponseEntity<List<StudentDTO>> getStudentsWithDoubtfulThesis(){
		List<StudentDTO> listOfStudents = new ArrayList<>();
		
		
		return new ResponseEntity<>(listOfStudents, HttpStatus.OK);
	}
	
	@GetMapping(value="/promoters")
	public ResponseEntity<List<PromoterDTO>> getAllPromoters(){
		List<PromoterDTO> listOfPromoters = new ArrayList<>();
		
		
		return new ResponseEntity<>(listOfPromoters, HttpStatus.OK);
	}
	
	@GetMapping(value="/promoters/{number}")
	public ResponseEntity<List<PromoterDTO>> getPromotersByNumberOfStudentsLead(@PathVariable("number") Integer studentsLead){
		List<PromoterDTO> listOfPromoters = new ArrayList<>();
		
		
		return new ResponseEntity<>(listOfPromoters, HttpStatus.OK);
	}
	
	@GetMapping(value="/thesis")
	public ResponseEntity<List<ThesisDTO>> getAllThesis(){
		List<ThesisDTO> listOfThesis = new ArrayList<>();
		
		
		return new ResponseEntity<>(listOfThesis, HttpStatus.OK);
	}
	
	@GetMapping(value="/students/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Integer studentId){
		StudentDTO studentDTO = new StudentDTO();
		
		
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/students/{thesis}")
	public ResponseEntity<StudentDTO> getStudentByThesisName(@PathVariable("thesis") String thesisName){
		StudentDTO studentDTO = new StudentDTO();
		
		
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@PostMapping(value="/students")
	public ResponseEntity<String> addStudent(@RequestBody StudentDTO studentDTO){
		String message = "";
		
		
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	@PostMapping(value="/promoters")
	public ResponseEntity<String> addPromoter(@RequestBody PromoterDTO promoterDTO){
		String message = "";
		
		
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	@PostMapping(value="/thesis")
	public ResponseEntity<String> addThesis(@RequestBody ThesisDTO thesisDTO){
		String message = "";
		
		
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/thesis/{studentId}")
	public ResponseEntity<String> updateThesisOfStudent(@RequestBody ThesisDTO thesisDTO, Integer studentId){
		String message = "";
		
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@PutMapping(value="/promoter/{studentId}/{promoterId}")
	public ResponseEntity<String> updatePromoterOfStudent(@PathVariable Integer studentId, @PathVariable Integer promoterId){
		String message = "";
		
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/students/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer studentId){
		String message = "";
		
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/promoters/{promoterId}")
	public ResponseEntity<String> deletePromoter(@PathVariable Integer promoterId){
		String message = "";
		
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/thesis/{thesisId}")
	public ResponseEntity<String> deleteThesis(@PathVariable Integer thesisId){
		String message = "";
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
