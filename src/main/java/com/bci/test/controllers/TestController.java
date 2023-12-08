package com.bci.test.controllers;


import com.bci.test.models.Error;
import com.bci.test.models.UserRequest;
import com.bci.test.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TestController {

    @Autowired
    private UserService service;

    @PostMapping("/api/v1/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        if (!violations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(violations.stream()
                            .map(v -> Error.builder()
                                    .mensaje(v.getPropertyPath() + " " + v.getMessage())
                                    .build())
                    .collect(Collectors.toList()));
        }

        try {
            return ResponseEntity.ok(service.createUser(userRequest));
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Error.builder()
                    .mensaje(ex.getMessage())
                    .build());
        }
    }

}
