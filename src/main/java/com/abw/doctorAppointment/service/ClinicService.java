package com.abw.doctorAppointment.service;

import com.abw.doctorAppointment.model.dto.ClinicDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClinicService {

    List<ClinicDTO> getAllClinics();
    ClinicDTO getClinicById(Long id);
    ClinicDTO createClinic(ClinicDTO clinic);
    ClinicDTO updateClinic(Long id, ClinicDTO clinic);
    void deleteClinic(Long id);
}
