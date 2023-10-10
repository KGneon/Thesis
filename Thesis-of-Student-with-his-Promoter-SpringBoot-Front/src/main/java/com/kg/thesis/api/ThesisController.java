package com.kg.thesis.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.dto.ThesisDTO;
import com.kg.thesis.exception.ThesisException;
import com.kg.thesis.service.ThesisService;

import java.util.List;

//CONTROLLER FOR THYMELEAF VERSION OF APP
@Controller
public class ThesisController {

	@Autowired
	ThesisService thesisService;
	
	// fields for controlling buttons
	private boolean showInfoText = false;
	private String infoText = " ";
	private boolean showStudentsButton1 = false;
    private boolean showStudentsBlankThesesButton2 = true;
    private boolean showStudentsWrongThesesButton3 = true;
    private String studentsSearchText = "All students: ";

	@GetMapping("/")
	public String homePage(ModelMap map) {
		map.put("homeview", "What you want to do?");
		return "homePage";
	}

    @GetMapping("/students")
    public String showAllStudentsPage(Model model) throws ThesisException {
    	showStudentsButton1 = false;
        showStudentsBlankThesesButton2 = true;
        showStudentsWrongThesesButton3 = true;
        studentsSearchText = "Students attempting to defend their theses:";
        model.addAttribute("studentsSearchText", studentsSearchText);
    	model.addAttribute("listOfStudents", thesisService.getStudents());
        model.addAttribute("showStudentsButton1", showStudentsButton1);
        model.addAttribute("showStudentsBlankThesesButton2", showStudentsBlankThesesButton2);
        model.addAttribute("showStudentsWrongThesesButton3", showStudentsWrongThesesButton3);
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
	@GetMapping("/studentUpdateForm/{id}")
	public String updateStudentForm(@PathVariable(value = "id") Integer id, Model model) throws ThesisException {
		StudentDTO studentDTO = thesisService.getStudentById(id);
		model.addAttribute("student", studentDTO);
		return "updateStudent";
	}

	@GetMapping("/promoterUpdateForm/{id}")
	public String updatePromoterForm(@PathVariable(value = "id") Integer id, Model model) throws ThesisException {
		PromoterDTO promoterDTO = thesisService.getPromoterById(id);
		model.addAttribute("promoter", promoterDTO);
		return "updatePromoter";
	}

	@GetMapping("/thesisUpdateForm/{id}")
	public String updateThesisForm(@PathVariable(value = "id") Integer id, Model model) throws ThesisException {
		ThesisDTO thesisDTO = thesisService.getThesisById(id);
		model.addAttribute("thesis", thesisDTO);
		return "updateThesis";
	}

	// ADDING STUDENT, PROMOTER, THESIS
	// model for student addition
	@GetMapping("/addNewStudentForm")
	public String newStudentForm(Model model) {
		// create model attribute to bind form data
		StudentDTO studentDTO = new StudentDTO();
		model.addAttribute("student", studentDTO);
		return "addStudent";
	}

	// model for promoter addition
	@GetMapping("/addNewPromoterForm")
	public String newPromoterForm(Model model) {
		// create model attribute to bind form data
		PromoterDTO promoterDTO = new PromoterDTO();
		model.addAttribute("promoter", promoterDTO);
		return "addPromoter";
	}

	// model for thesis addition
	@GetMapping("/addNewThesisForm")
	public String newThesisForm(Model model, ThesisDTO thesisDTO) {
		// create model attribute to bind form data
		model.addAttribute("listOfThesisTypes", List.of("BACHELOR", "ENGINEER", "MASTER", "DOCTOR"));
		/*ThesisDTO thesisDTO = new ThesisDTO();*/
		model.addAttribute("thesis", thesisDTO);
		return "addThesis";
	}

	// UPDATE

	// POST request just to accept saving new or updated entities
	// accepting and saving student
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") StudentDTO studentDTO) throws ThesisException {
		thesisService.addStudent(studentDTO);
		return "redirect:/students";
	}

	// accepting and saving promoter
	@PostMapping("/savePromoter")
	public String savePromoter(@ModelAttribute("promoter") PromoterDTO promoterDTO) throws ThesisException {
		thesisService.addPromoter(promoterDTO);
		return "redirect:/promoters";
	}

	// accepting and saving thesis
	@PostMapping("/addNewThesisForm")
	public String saveThesis(@Valid ThesisDTO thesisDTO, BindingResult result, Model model) throws ThesisException {
		if (result.hasErrors()) {
			return "addThesis";
		}
		thesisService.addThesis(thesisDTO);
		return "redirect:/theses";
	}

	/////////////
	// getting models for deletion
	@GetMapping("/studentDeleteForm/{id}")
	public String deleteStudentForm(@PathVariable(value = "id") Integer id, Model model) throws ThesisException {
		StudentDTO studentDTO = thesisService.getStudentById(id);
		model.addAttribute("student", studentDTO);
		return "deleteStudent";
	}

	@GetMapping("/promoterDeleteForm/{id}")
	public String deletePromoterForm(@PathVariable(value = "id") Integer id, Model model) throws ThesisException {
		PromoterDTO promoterDTO = thesisService.getPromoterById(id);
		model.addAttribute("promoter", promoterDTO);
		return "deletePromoter";
	}

	@GetMapping("/thesisDeleteForm/{id}")
	public String deleteThesisForm(@PathVariable(value = "id") Integer id, Model model) throws ThesisException {
		ThesisDTO thesisDTO = thesisService.getThesisById(id);
		model.addAttribute("thesis", thesisDTO);
		return "deleteThesis";
	}

	// POST request after accepting the deletion to DELETE
	@PostMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("studentId") Integer studentId) throws ThesisException {
		thesisService.deleteStudent(studentId);
		return "redirect:/students";
	}

	@PostMapping("/deletePromoter")
	public String deletePromoter(@RequestParam("promoterId") Integer promoterId) throws ThesisException {
		thesisService.deletePromoter(promoterId);
		return "redirect:/promoters";
	}

	@PostMapping("/deleteThesis")
	public String deleteThesis(@RequestParam("thesisId") Integer thesisId) throws ThesisException {
		thesisService.deleteThesis(thesisId);
		return "redirect:/theses";
	}

	//details pages
	@GetMapping("/thesisDetails/{id}")
	public String thesisDetailsForm(@PathVariable(value = "id") Integer id, Model model) throws ThesisException {
		ThesisDTO thesisDTO = thesisService.getThesisById(id);
		model.addAttribute("thesis", thesisDTO);
		return "thesisDetailsPage";
	}
	
	@GetMapping("/promoterDetails/{id}")
	public String promoterDetailsForm(@PathVariable(value = "id") Integer id, Model model) throws ThesisException {
		PromoterDTO promoterDTO = thesisService.getPromoterById(id);
		model.addAttribute("promoter", promoterDTO);
		return "promoterDetailsPage";
	}
	
	// GETTERS WITH conditions
	@GetMapping("/promoters/possibleAllocation/{studentId}")
	public String getPromotersWithPossibleStudentAllocation(@PathVariable(value = "studentId") Integer studentId, Model model) throws ThesisException {
		model.addAttribute("listOfPossiblePromotersForAllocation", thesisService.getPromotersWithPossibleStudentAllocation());
		return "showPromotersAllocationCondition";
	}

	@GetMapping("/students/no_theses")
	public String getStudentsWithoutThesis(Model model) throws ThesisException {
		model.addAttribute("studentsSearchText", studentsSearchText);
		model.addAttribute("listOfStudents", thesisService.getStudentsWithDoubtfulThesis(false));
		model.addAttribute("showStudentsButton1", showStudentsButton1);
        model.addAttribute("showStudentsBlankThesesButton2", showStudentsBlankThesesButton2);
        model.addAttribute("showStudentsWrongThesesButton3", showStudentsWrongThesesButton3);
		return "showAllStudents";
	}
	
	@GetMapping("/students/mismatched_theses_field")
	public String getStudentsWithThesisOutOfField(Model model) throws ThesisException {
		model.addAttribute("studentsSearchText", studentsSearchText);
		model.addAttribute("listOfStudents", thesisService.getStudentsWithDoubtfulThesis(true));
		model.addAttribute("showStudentsButton1", showStudentsButton1);
        model.addAttribute("showStudentsBlankThesesButton2", showStudentsBlankThesesButton2);
        model.addAttribute("showStudentsWrongThesesButton3", showStudentsWrongThesesButton3);
		return "showAllStudents";
	}

	//Switching buttons controlling conditions
    @PostMapping("/students/form")
    public String handleButton1() {
        // Handle button 1 click
    	studentsSearchText = "Students attempting to defend their theses:";
    	showStudentsButton1 = false;
    	showStudentsBlankThesesButton2 = true;
    	showStudentsWrongThesesButton3 = true;
        return "redirect:/students";
    }

    @PostMapping("/students/no_theses/form")
    public String handleButton2() {
        // Handle button 2 click
    	studentsSearchText = "Students with a thesis topic not yet chosen: ";
    	showStudentsButton1 = true;
    	showStudentsBlankThesesButton2 = false;
    	showStudentsWrongThesesButton3 = true;
        return "redirect:/students/no_theses";
    }

    @PostMapping("/students/mismatched_theses_field/form")
    public String handleButton3() {
        // Handle button 3 click
    	studentsSearchText = "Students with a thesis topic that does not match the promoter's specialization (field of knowledge): ";
    	showStudentsButton1 = true;
    	showStudentsBlankThesesButton2 = true;
    	showStudentsWrongThesesButton3 = false;
        return "redirect:/students/mismatched_theses_field";
    }

}
