package com.abw.doctorAppointment.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ClinicDTO {

    private Long id;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private DoctorDTO doctorDTO;
}
