package com.kg.thesis.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

//TRAINING CONTROLLER FOR THYMELEAF
@Controller
public class ThesisController {
	
	@GetMapping("/")
	public String homePage(ModelMap map) { //TO JEST ODNIESIENIE DO PLIKU HTML
		map.put("homeview", "What you want to do?"); //TO JEST ODNIESIENIE DO ${} W PLIKU
		return "homePage";
	}

}
