package com.report_service.report.repository;

import com.report_service.report.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

    Students findByName(String name);

}
