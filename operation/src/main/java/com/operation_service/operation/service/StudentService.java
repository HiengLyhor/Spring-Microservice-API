package com.operation_service.operation.service;

import com.operation_service.operation.dto.StudentCreateRequest;
import com.operation_service.operation.dto.StudentsDto;
import com.operation_service.operation.feign.FeignClientReport;
import com.operation_service.operation.model.StudentDetail;
import com.operation_service.operation.model.Students;
import com.operation_service.operation.repository.StudentDetailRepository;
import com.operation_service.operation.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentDetailRepository studentDetailRepository;

    @Autowired
    FeignClientReport clientReport;

    public ResponseEntity<?> saveStudent(StudentCreateRequest request) {

        try {

            StudentsDto studentByName = clientReport.getStudentByName(request.getName());

            if (studentByName.getName() != null) {
                String message = "This student already exists.";
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            }

            Students students = new Students();
            StudentDetail studentDetail = new StudentDetail();

            BeanUtils.copyProperties(request, students);
            BeanUtils.copyProperties(request, studentDetail);

            studentDetail.setStudent(students);
            students.setStudentDetail(studentDetail);

            studentRepository.save(students);
            studentDetailRepository.save(studentDetail);

            return ResponseEntity.ok(request);

        }
        catch (Exception ex) {
            throw ex;
        }

    }

}
