package com.report_service.report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class StudentDetailDto {

    String grade;

    String parentContact;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Timestamp enrollDate;

    Long issueCount;

}
