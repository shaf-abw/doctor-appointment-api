package com.abw.doctorAppointment.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class DoctorDTO {

    private Long id;
    private String name;
    private String specialty;

    @Email(message = "Input must be in Email format")
    private String email;

    @Pattern(regexp = "^\\+84[0-9]{9,10}$|^0[0-9]{9,10}$", message = "The mobile number is not in the correct format")
    private String mobile;

    private List<ClinicDTO> clinics;
}
