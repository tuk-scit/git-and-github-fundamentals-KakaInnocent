package com.net.tmi.web.dto;

public class AppointmentDto {

	private String name;
	private String email;
	private String description;
	private String diagnosis;
	private String treatment;
	private String regTime;
	
	public AppointmentDto() {
		
	}
	

	
	public AppointmentDto(String name, String email, String description) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
	}
	



	public AppointmentDto(String name, String email, String description, String diagnosis, String treatment) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
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



	public String getRegTime() {
		return regTime;
	}



	public void setRegTime(String regTime) {
		this.regTime = regTime;
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
	
	
	
}
