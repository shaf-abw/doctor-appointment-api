package com.abw.doctorAppointment.service;

import com.abw.doctorAppointment.model.dto.request.Register;
import com.abw.doctorAppointment.model.entity.User;

public interface UserService {

    User register(Register register);
}
