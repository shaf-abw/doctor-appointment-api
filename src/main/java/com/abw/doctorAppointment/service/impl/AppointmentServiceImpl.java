package com.abw.doctorAppointment.service.impl;

import com.abw.doctorAppointment.model.dto.AppointmentDTO;
import com.abw.doctorAppointment.model.entity.Appointment;
import com.abw.doctorAppointment.model.entity.Status;
import com.abw.doctorAppointment.repository.AppointmentRepository;
import com.abw.doctorAppointment.service.AppointmentService;
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
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        log.info("AppointmentServiceImpl : getAllAppointments");
        return appointmentRepository.findAll()
                .stream()
                .map((element) -> modelMapper.map(element, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        log.info("AppointmentServiceImpl : getAppointmentById");
        return appointmentRepository.findById(id)
                .map((element) -> modelMapper.map(element, AppointmentDTO.class))
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        log.info("AppointmentServiceImpl : createAppointment");
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        appointment.setStatus(Status.Booked);
        return modelMapper.map(appointmentRepository.save(appointment), AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        log.info("AppointmentServiceImpl : updateAppointment");
        Appointment updatedAppointment = modelMapper.map(appointmentDTO, Appointment.class);
        return modelMapper.map(appointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
                    appointment.setStatus(updatedAppointment.getStatus());
                    return appointmentRepository.save(appointment);
                }).orElseThrow(() -> new RuntimeException("Appointment not found")), AppointmentDTO.class);
    }

    @Override
    public void deleteAppointment(Long id) {
        log.info("AppointmentServiceImpl : deleteAppointment");
        appointmentRepository.deleteById(id);
    }
}
