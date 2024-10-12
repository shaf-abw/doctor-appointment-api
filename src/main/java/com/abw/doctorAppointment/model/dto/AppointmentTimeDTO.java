package com.abw.doctorAppointment.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class AppointmentTimeDTO {

    private Long id;
    private String time;
    private Long clinicId;

    @JsonIgnore
    private ClinicDTO clinic;

    public Long getClinicId() {
        return this.clinic != null ? this.clinic.getId() : null;
    }
}
