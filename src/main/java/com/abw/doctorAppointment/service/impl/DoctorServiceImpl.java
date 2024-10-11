package com.abw.doctorAppointment.service.impl;

import com.abw.doctorAppointment.model.dto.DoctorDTO;
import com.abw.doctorAppointment.model.entity.Doctor;
import com.abw.doctorAppointment.repository.DoctorRepository;
import com.abw.doctorAppointment.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        log.info("DoctorServiceImpl : getAllDoctors");
        return doctorRepository.findAll()
                .stream()
                .map((element) -> modelMapper.map(element, DoctorDTO.class))
                .toList();
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        log.info("DoctorServiceImpl : getDoctorById");
        return doctorRepository.findById(id)
                .map((element) -> modelMapper.map(element, DoctorDTO.class))
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        log.info("DoctorServiceImpl : createDoctor");
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        return modelMapper.map(doctorRepository.save(doctor), DoctorDTO.class);
    }

    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        log.info("DoctorServiceImpl : updateDoctor");
        Doctor updatedDoctor = modelMapper.map(doctorDTO, Doctor.class);
        return modelMapper.map(doctorRepository.findById(id)
                .map(doctor -> {
                    doctor.setName(updatedDoctor.getName());
                    doctor.setSpecialty(updatedDoctor.getSpecialty());
                    doctor.setEmail(updatedDoctor.getEmail());
                    doctor.setMobile(updatedDoctor.getMobile());
                    return doctorRepository.save(doctor);
                }).orElseThrow(() -> new RuntimeException("Doctor not found")), DoctorDTO.class);
    }

    @Override
    public void deleteDoctor(Long id) {
        log.info("DoctorServiceImpl : deleteDoctor");
        doctorRepository.deleteById(id);
    }
}
