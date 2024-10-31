package com.abw.doctorAppointment.service;

import com.abw.doctorAppointment.model.entity.Role;
import com.abw.doctorAppointment.model.entity.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName name);
}
