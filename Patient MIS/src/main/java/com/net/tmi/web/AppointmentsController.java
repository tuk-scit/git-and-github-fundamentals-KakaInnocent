package com.net.tmi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.net.tmi.entity.Appointment;
import com.net.tmi.service.AppointmentService;

@Controller
@RequestMapping("/doctor")
public class AppointmentsController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping
	public String viewHomePage() {
		return "tmi/doctor";
	}
	
	//Method Handler to display list of Appointments
	@GetMapping("/viewAppointments")
	public String viewAppointments(Model model) {
		return findPaginated(1, model);
	}
	
	//Method handler To handle show newPatient Form
	@GetMapping("/showNewPatientsForm")
	public String showNewPatientsForm(Model model) {
		Appointment appointment=new Appointment();
		model.addAttribute("appointment", appointment);
		return "new_patient";
	}
	//Method handler to handle Save new Patients information-Who may have no access to smartphones
	@PostMapping("/savePatient")
	public String saveEmployee(@ModelAttribute("appointment") Appointment appointment) {
		appointmentService.savePatient(appointment);
		return "redirect:/doctor/viewAppointments";
	}
	//Method handler to handle showFormForUpdate request from Doctors page
	@GetMapping("/showFormForUpadate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		//get Appointment from the service
		Appointment appointment=appointmentService.getAppointmentById(id);
		model.addAttribute("appointment", appointment);
		return "update_appointment";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value="pageNo") int pageNo, Model model) {
		int pageSize=10;
		
		Page<Appointment> page=appointmentService.findPaginated(pageNo, pageSize);
		List<Appointment> listAppointments=page.getContent();
		
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listAppointments", listAppointments);
		
		return "appointments1";	
	}
}
