package com.net.tmi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.net.tmi.entity.User;
import com.net.tmi.service.AppointmentService;
import com.net.tmi.service.UserService;
import com.net.tmi.web.dto.AppointmentDto;

@Controller
public class AppController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@PostMapping("/makeApp")
	public String makeAppointment(@ModelAttribute("appointment") AppointmentDto regAppointmentDto){
		appointmentService.save(regAppointmentDto);
		return "redirect:/app?success";
	}

	@GetMapping("/app")
	public String showAppForm(Model model) {
		model.addAttribute("appointment", new AppointmentDto());
		return "app";
	}
	
	//Method handler to handle login
	  @GetMapping("/login") public String login() { return "login"; }
	 
	
	
	
	
	
	
	
	
	
	@GetMapping("/")
	public String viewHomePage() {
		return "tmi/index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user",new User());
		return "signup_form";
	}
	
	//Method handler That handles users Registration form data request
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		userService.saveUserWithDefaultRole(user);
		return "redirect:/register?success";
	}
	
	
	  //Method handler to handle list_users request from index.html page navigation bar
	  
		/*
		 * // @GetMapping("/list_users") public String viewUserList(Model model) { //
		 * List<User> listUsers=userService.listAll(); model.addAttribute("listUsers",
		 * // listUsers); return "users"; }
		 * 
		 * 
		 * // @GetMapping("/admin/edit/{id}") // public String
		 * editUser(@PathVariable("id") Long id,Model model) { // User user=
		 * userService.get(id); // List<Role> listRoles= userService.getRoles(); //
		 * model.addAttribute("user", user); // model.addAttribute("listRoles",
		 * listRoles); // return "user_form"; // }
		 * 
		 * // @PostMapping("/admin/save") // public String saveUser(User user) { //
		 * userService.save(user); // return "redirect:/list_users"; // }
		 */	
}
