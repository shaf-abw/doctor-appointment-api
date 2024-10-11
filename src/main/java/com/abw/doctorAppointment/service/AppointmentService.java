package com.abw.doctorAppointment.service;

import com.abw.doctorAppointment.model.dto.AppointmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AppointmentService {

    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO getAppointmentById(Long id);
    AppointmentDTO createAppointment(AppointmentDTO appointment);
    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointment);
    void deleteAppointment(Long id);
}
