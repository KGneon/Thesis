package com.kg.thesis.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.dto.ThesisDTO;
import com.kg.thesis.exception.ThesisException;
import com.kg.thesis.service.ThesisService;

//TRAINING CONTROLLER FOR THYMELEAF
@Controller
public class ThesisController {
	
	@Autowired
	ThesisService thesisService;
	
	@GetMapping("/")
	public String homePage(ModelMap map) { //TO JEST ODNIESIENIE DO PLIKU HTML
		map.put("homeview", "What you want to do?"); //TO JEST ODNIESIENIE DO ${} W PLIKU
		return "homePage";
	}
	
	@GetMapping("/students")
	public String allStudentsPage(Model model) throws ThesisException {
		model.addAttribute("listOfStudents", thesisService.getStudents());
		return "showAllStudents";
	}

	@GetMapping("/promoters")
	public String allPromotersPage(Model model) throws ThesisException {
		model.addAttribute("listOfPromoters", thesisService.getPromoters());
		return "showAllPromoters";
	}
	
	@GetMapping("/theses")
	public String allThesisPage(Model model) throws ThesisException {
		model.addAttribute("listOfTheses", thesisService.getTheses());
		return "showAllTheses";
	}
	
	//ADDING STUDENT, PROMOTER, THESIS
	
	//model for student addition
	@GetMapping("/addStudent")
	 public String newStudentForm(Model model) {
	     // create model attribute to bind form data
		StudentDTO studentDTO = new StudentDTO();
	     model.addAttribute("student", studentDTO);
	     return "addNewStudent";
	}
	
	//accepting and saving student
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") StudentDTO studentDTO) throws ThesisException {
		thesisService.addStudent(studentDTO);
		return "redirect:/showAllStudents";
	}
	
	//model for promoter addition
	@GetMapping("/addPromoter")
	 public String newPromoterForm(Model model) {
	     // create model attribute to bind form data
		PromoterDTO promoterDTO = new PromoterDTO();
	     model.addAttribute("promoter", promoterDTO);
	     return "addNewPromoter";
	}
	
	//accepting and saving promoter
	@PostMapping("/savePromoter")
	public String savePromoter(@ModelAttribute("promoter") PromoterDTO promoterDTO) throws ThesisException {
		thesisService.addPromoter(promoterDTO);
		return "redirect:/showAllPromoters";
	}
	
	//model for thesis addition
	@GetMapping("/addThesis")
	 public String newThesisForm(Model model) {
	     // create model attribute to bind form data
		ThesisDTO thesisDTO = new ThesisDTO();
	     model.addAttribute("thesis", thesisDTO);
	     return "addNewThesis";
	}
	
	//accepting and saving thesis
	@PostMapping("/saveThesis")
	public String saveThesis(@ModelAttribute("thesis") ThesisDTO thesisDTO) throws ThesisException {
		thesisService.addThesis(thesisDTO);
		return "redirect:/showAllTheses";
	}

}
