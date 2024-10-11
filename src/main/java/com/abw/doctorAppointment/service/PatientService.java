package com.abw.doctorAppointment.service;

import com.abw.doctorAppointment.model.dto.PatientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    List<PatientDTO> getAllPatients();
    PatientDTO getPatientById(Long id);
    PatientDTO createPatient(PatientDTO patient);
    PatientDTO updatePatient(Long id, PatientDTO patient);
    void deletePatient(Long id);

}
