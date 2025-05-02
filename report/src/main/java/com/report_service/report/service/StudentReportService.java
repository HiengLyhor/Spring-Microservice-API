package com.report_service.report.service;

import com.report_service.report.dto.StudentDetailDto;
import com.report_service.report.dto.StudentReportByGradeDto;
import com.report_service.report.dto.StudentsDto;
import com.report_service.report.model.StudentDetail;
import com.report_service.report.model.Students;
import com.report_service.report.repository.StudentDetailRepository;
import com.report_service.report.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentReportService {

    @Autowired
    StudentDetailRepository studentDetailRepository;

    @Autowired
    StudentRepository studentRepository;

    public StudentsDto getStudentDetail(Long id) {

        try {

            StudentsDto result = new StudentsDto();

            Optional<Students> studentById = studentRepository.findById(id);

            if (studentById.isPresent()) {

                Optional<StudentDetail> studentDetail = studentDetailRepository.findByStudent(studentById.get());

                BeanUtils.copyProperties(studentById.get(), result);

                if (studentDetail.isPresent()) {

                    StudentDetailDto studentDetailDto = new StudentDetailDto();

                    BeanUtils.copyProperties(studentDetail.get(), studentDetailDto);

                    result.setDetail(studentDetailDto);

                }

            }

            return result;

        }
        catch (Exception ex) {
            throw ex;
        }

    }

    public List<StudentsDto> getAllStudents() {

        try {

            List<StudentsDto> result = new ArrayList<>();

            List<Students> listAllStudents = studentRepository.findAll();

            for(Students studentLoop : listAllStudents) {

                StudentsDto singleStudent = new StudentsDto();
                BeanUtils.copyProperties(studentLoop, singleStudent);

                Optional<StudentDetail> studentDetail = studentDetailRepository.findByStudent(studentLoop);

                if (studentDetail.isPresent()) {
                    StudentDetailDto detail = new StudentDetailDto();
                    BeanUtils.copyProperties(studentDetail.get(), detail);
                    singleStudent.setDetail(detail);
                }

                result.add(singleStudent);

            }

            return result;

        }
        catch (Exception ex) {
            throw ex;
        }

    }

    public List<StudentDetail> getStudentByGrade(String grade) {

        try {

            List<StudentDetail> result = new ArrayList<>();

            StudentDetail studentDetail = new StudentDetail();
            Students students = new Students();

            students.setName("Data Unavailable.");
            studentDetail.setStudent(students);

            result.add(studentDetail);

            return result;

        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public StudentsDto getStudentByName(String name) {

        try {

            StudentsDto resStudentsDto = new StudentsDto();

            Students studentByName = studentRepository.findByName(name);

            if (studentByName != null) {

                Optional<StudentDetail> studentDetail = studentDetailRepository.findByStudent(studentByName);

                BeanUtils.copyProperties(studentByName, resStudentsDto);

                StudentDetailDto studentDetailDto = new StudentDetailDto();

                studentDetail.ifPresent(detail -> BeanUtils.copyProperties(detail, studentDetailDto));

            }

            return resStudentsDto;

        }
        catch (Exception ex) {
            throw ex;
        }
    }
}
