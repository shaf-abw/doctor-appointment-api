package com.abw.doctorAppointment.service.impl;

import com.abw.doctorAppointment.exception.RoleNotFoundException;
import com.abw.doctorAppointment.model.entity.Role;
import com.abw.doctorAppointment.model.entity.RoleName;
import com.abw.doctorAppointment.repository.RoleRepository;
import com.abw.doctorAppointment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return Optional.ofNullable(roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role Not Found with name: " + name)));
    }
}
