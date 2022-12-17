package com.net.tmi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name = "email")
	private String email;
	
	private String description;
	private String regTime;
	private String diagnosis;
	private String treatment;
	



public Appointment() {
	
}



	public Appointment(String name, String email, String description) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
	}



	public Appointment(Long id, String name, String email, String description, String diagnosis, String treatment) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.description = description;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
	}
	
	



	public Appointment(String name, String email, String description, String diagnosis, String treatment) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	
	
	
}
