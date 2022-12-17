package com.net.tmi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.net.tmi.entity.Appointment;
import com.net.tmi.repository.AppointmentRepository;
import com.net.tmi.web.dto.AppointmentDto;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	
	@Override
	public Appointment save(AppointmentDto regAppointmentDto) {
		Appointment appointment=new Appointment(regAppointmentDto.getName(),regAppointmentDto.getEmail(),regAppointmentDto.getDescription());
		return appointmentRepository.save(appointment);
	}


	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}


	@Override
	public void savePatient(Appointment appointment) {
		this.appointmentRepository.save(appointment);
		
	}


	@Override
	public Appointment getAppointmentById(Long id) {
		 Optional<Appointment> optional= appointmentRepository.findById(id);
		 Appointment appointment=null;
		 if (optional.isPresent()) {
			appointment=optional.get();
		}else {
			throw new RuntimeException("Appointment not found for Patient Id::" +id);
		}
		return appointment;
	}


	@Override
	public Page<Appointment> findPaginated(int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		
		return this.appointmentRepository.findAll(pageable);
	}

}
