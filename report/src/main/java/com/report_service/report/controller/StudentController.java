package com.report_service.report.controller;

import com.report_service.report.dto.StudentReportByGradeDto;
import com.report_service.report.dto.StudentsDto;
import com.report_service.report.service.StudentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("report/")
public class StudentController {

    @Autowired
    StudentReportService service;

    @GetMapping("/")
    String defaultController() {
        return "Hello from Student Report API!";
    }

    @GetMapping("student/{id}")
    StudentsDto getStudentById(@PathVariable Long id) {
        return service.getStudentDetail(id);
    }

    @GetMapping("students")
    List<StudentsDto> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("student-grade/{grade}")
    StudentReportByGradeDto studentReportByGrade(@PathVariable String grade) {
        return service.getStudentByGrade(grade);
    }

    @GetMapping("student-name/{name}")
    StudentsDto getStudentByName(@PathVariable String name) {
        return service.getStudentByName(name);
    }

}
