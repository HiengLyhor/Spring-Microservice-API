package com.operation_service.operation.controller;

import com.operation_service.operation.dto.StudentCreateRequest;
import com.operation_service.operation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operation/")
public class StudentOperationController {

    @Autowired
    StudentService service;

    @PostMapping("create")
    ResponseEntity<?> saveStudent(@RequestBody StudentCreateRequest request) {
        return service.saveStudent(request);
    }

}
