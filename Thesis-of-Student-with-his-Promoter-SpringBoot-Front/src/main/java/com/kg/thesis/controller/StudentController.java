package com.kg.thesis.controller;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.exception.ThesisException;
import com.kg.thesis.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class StudentController {
    @Autowired
    ThesisService thesisService;
    //GET ALL
    @GetMapping(value="/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() throws ThesisException {
        List<StudentDTO> listOfStudents = thesisService.getStudents();
        //return new ResponseEntity<>(listOfStudents, HttpStatus.OK);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value="/promoters")
    public ResponseEntity<List<PromoterDTO>> getAllPromoters() throws ThesisException{
        List<PromoterDTO> listOfPromoters = thesisService.getPromoters();
        return new ResponseEntity<>(listOfPromoters, HttpStatus.OK);
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

}
