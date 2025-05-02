package com.report_service.report.repository;

import com.report_service.report.model.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDetailRepository extends JpaRepository<StudentDetail, Long> {

    List<StudentDetail> findByGrade(String grade);

}
