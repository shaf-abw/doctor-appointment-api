package com.abw.doctorAppointment.api;

import com.abw.doctorAppointment.model.dto.request.Register;
import com.abw.doctorAppointment.model.entity.User;
import com.abw.doctorAppointment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Register register) {
        log.info("UserController : register --> {}", register.toString());
        return ResponseEntity.ok(userService.register(register));
    }
}
