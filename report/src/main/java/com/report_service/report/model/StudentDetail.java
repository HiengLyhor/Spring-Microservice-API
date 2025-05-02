package com.report_service.report.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "StudentDetail")
public class StudentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @OneToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Students student;

    @Column(name = "GRADE")
    String grade;

    @Column(name = "PARENT_CONTACT")
    String parentContact;

    @Column(name = "ENROLL_DATE")
    Timestamp enrollDate;

    @Column(name = "ISSUE_COUNT")
    Long issueCount;

}
