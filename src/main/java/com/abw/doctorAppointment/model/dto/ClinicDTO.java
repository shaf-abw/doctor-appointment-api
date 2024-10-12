package com.abw.doctorAppointment.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private Long doctorId;
    private List<AppointmentTimeDTO> appointmentTimes;

    @JsonIgnore
    private DoctorDTO doctor;

    public Long getDoctorId() {
        return this.doctor != null ? this.doctor.getId() : null;
    }
}
