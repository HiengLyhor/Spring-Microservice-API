package com.report_service.report.repository;

import com.report_service.report.model.StudentDetail;
import com.report_service.report.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentDetailRepository extends JpaRepository<StudentDetail, Long> {

    List<StudentDetail> findByGrade(String grade);

    Optional<StudentDetail> findByStudent(Students students);

}
