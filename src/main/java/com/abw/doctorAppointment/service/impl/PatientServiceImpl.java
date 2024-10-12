package com.abw.doctorAppointment.service.impl;

import com.abw.doctorAppointment.exception.ResourceNotFoundException;
import com.abw.doctorAppointment.model.dto.PatientDTO;
import com.abw.doctorAppointment.model.entity.Patient;
import com.abw.doctorAppointment.repository.PatientRepository;
import com.abw.doctorAppointment.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatientDTO> getAllPatients() {
        log.info("PatientServiceImpl : getAllPatients");
        return patientRepository.findAll()
                .stream()
                .map((element) -> modelMapper.map(element, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        log.info("PatientServiceImpl : getPatientById");
        return patientRepository.findById(id)
                .map((element) -> modelMapper.map(element, PatientDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        log.info("PatientServiceImpl : createPatient");
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        return modelMapper.map(patientRepository.save(patient), PatientDTO.class);
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        log.info("PatientServiceImpl : updatePatient");
        Patient updatedPatient = modelMapper.map(patientDTO, Patient.class);
        return modelMapper.map(patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(updatedPatient.getName());
                    patient.setEmail(updatedPatient.getEmail());
                    patient.setMobile(updatedPatient.getMobile());
                    return patientRepository.save(patient);
                }).orElseThrow(() -> new ResourceNotFoundException("Patient not found")), PatientDTO.class);
    }

    @Override
    public void deletePatient(Long id) {
        log.info("PatientServiceImpl : deletePatient");
        patientRepository.deleteById(id);
    }
}
