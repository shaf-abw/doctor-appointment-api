package com.abw.doctorAppointment.service.impl;

import com.abw.doctorAppointment.model.dto.request.Login;
import com.abw.doctorAppointment.model.dto.request.Register;
import com.abw.doctorAppointment.model.entity.RoleName;
import com.abw.doctorAppointment.model.entity.User;
import com.abw.doctorAppointment.repository.UserRepository;
import com.abw.doctorAppointment.security.jwt.JwtService;
import com.abw.doctorAppointment.service.RoleService;
import com.abw.doctorAppointment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public User register(Register register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setRoles(register.getRoles()
                .stream()
                .map(role -> roleService.findByName(mapToRoleName(role))
                        .orElseThrow(() -> new RuntimeException("Role not found in the database.")))
                .collect(Collectors.toSet()));
        return userRepository.save(user);
    }

    @Override
    public String login(Login login) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(login.getUsername());
        }

        return "Fail";
    }

    private RoleName mapToRoleName(String roleName) {
        return switch (roleName) {
            case "ADMIN", "admin", "Admin" -> RoleName.ADMIN;
            case "PM", "pm", "Pm" -> RoleName.PM;
            case "USER", "user", "User" -> RoleName.USER;
            default -> null;
        };
    }
}
