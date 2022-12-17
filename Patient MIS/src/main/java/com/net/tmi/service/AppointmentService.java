package com.net.tmi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.net.tmi.entity.Appointment;
import com.net.tmi.web.dto.AppointmentDto;

public interface AppointmentService {

	Appointment save(AppointmentDto regAppointmentDto);
	List<Appointment> getAllAppointments();
	void savePatient(Appointment appointment);
	Appointment getAppointmentById(Long id);
	Page<Appointment> findPaginated(int pageNo,int pageSize);
	
}
