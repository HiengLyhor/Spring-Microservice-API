package com.report_service.report.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class StudentReportByGradeDto {

    String grade;

    List<StudentsDto> studentData;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<StudentsDto> getStudentData() {
        return studentData;
    }

    public void setStudentData(List<StudentsDto> studentData) {
        this.studentData = studentData;
    }
}
