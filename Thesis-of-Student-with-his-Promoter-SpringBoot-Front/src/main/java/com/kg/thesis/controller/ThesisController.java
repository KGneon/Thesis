package com.kg.thesis.controller;

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
import com.kg.thesis.exception.ThesisException;
import com.kg.thesis.service.ThesisService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
@Validated
public class ThesisController {
	
	@Autowired
	ThesisService thesisService;
	
	@Autowired
	Environment environment;

	@GetMapping(value="/theses")
	public ResponseEntity<List<ThesisDTO>> getAllTheses() throws ThesisException{
		List<ThesisDTO> listOfTheses = thesisService.getTheses();
		return new ResponseEntity<>(listOfTheses, HttpStatus.OK);
	}

	@PostMapping(value="/theses/add")
	public ResponseEntity<String> addThesis(@RequestBody ThesisDTO thesisDTO) throws ThesisException{
		thesisService.addThesis(thesisDTO);
		String message = "API.THESIS_ADDED";
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	//UPDATE
	//do ogarniecia
	@PutMapping(value="/theses/update")
	public ResponseEntity<String> updateThesisOfStudent(@RequestBody ThesisDTO thesisDTO) throws ThesisException{
		//thesisService.addThesis(thesisDTO);
		String message = "";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	//do ogarniecia
	@PutMapping(value="/promoters/{studentId}/{promoterId}")
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
	@DeleteMapping(value="/theses/{thesisId}")
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
	@GetMapping(value="/promoters/{id}")
	public ResponseEntity<PromoterDTO> getPromoterById(@PathVariable("id") Integer promoterId) throws ThesisException{
		PromoterDTO promoterDTO = thesisService.getPromoterById(promoterId);
		return new ResponseEntity<>(promoterDTO, HttpStatus.OK);
	}
	@GetMapping(value="/theses/{id}")
	public ResponseEntity<ThesisDTO> getThesisById(@PathVariable("id") Integer thesisId) throws ThesisException{
		ThesisDTO thesisDTO = thesisService.getThesisById(thesisId);
		return new ResponseEntity<>(thesisDTO, HttpStatus.OK);
	}

}
