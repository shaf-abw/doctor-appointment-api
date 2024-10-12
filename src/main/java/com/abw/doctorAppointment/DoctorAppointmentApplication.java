package com.abw.doctorAppointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/*
* Done:
* Doctor -> add, getall, getbyid, delete
* Patient -> add, getall, getbyid, delete, update
* Appointment -> add, getall, getbyid, delete, update
*
* Doctor update not working
* Security needs to be config
* */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DoctorAppointmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorAppointmentApplication.class, args);
	}

}
