package com.abw.doctorAppointment.service.impl;

import com.abw.doctorAppointment.exception.ResourceNotFoundException;
import com.abw.doctorAppointment.model.dto.ClinicDTO;
import com.abw.doctorAppointment.model.entity.Clinic;
import com.abw.doctorAppointment.repository.ClinicRepository;
import com.abw.doctorAppointment.service.ClinicService;
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
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ClinicDTO> getAllClinics() {
        log.info("ClinicServiceImpl : getAllClinics");
        return clinicRepository.findAll()
                .stream()
                .map((element) -> modelMapper.map(element, ClinicDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClinicDTO getClinicById(Long id) {
        log.info("ClinicServiceImpl : getClinicById");
        return clinicRepository.findById(id)
                .map((element) -> modelMapper.map(element, ClinicDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Clinic not found"));
    }

    @Override
    public ClinicDTO createClinic(ClinicDTO clinicDTO) {
        log.info("ClinicServiceImpl : createClinic");
        Clinic clinic = modelMapper.map(clinicDTO, Clinic.class);
        return modelMapper.map(clinicRepository.save(clinic), ClinicDTO.class);
    }

    @Override
    public ClinicDTO updateClinic(Long id, ClinicDTO clinicDTO) {
        log.info("ClinicServiceImpl : updateClinic");
        Clinic updatedClinic = modelMapper.map(clinicDTO, Clinic.class);
        return modelMapper.map(clinicRepository.findById(id)
                .map(clinic -> {
                    clinic.setName(updatedClinic.getName());
                    clinic.setAddress1(updatedClinic.getAddress1());
                    clinic.setAddress2(updatedClinic.getAddress2());
                    clinic.setCity(updatedClinic.getCity());
                    clinic.setState(updatedClinic.getState());
                    return clinicRepository.save(clinic);
                }).orElseThrow(() -> new ResourceNotFoundException("Clinic not found")), ClinicDTO.class);
    }

    @Override
    public void deleteClinic(Long id) {
        log.info("ClinicServiceImpl : deleteClinic");
        clinicRepository.deleteById(id);
    }
}
