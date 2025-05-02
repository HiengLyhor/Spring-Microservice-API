package com.operation_service.operation.feign;

import com.operation_service.operation.dto.StudentsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("REPORT")
public interface FeignClientReport {

    @GetMapping("report/student-name/{name}")
    StudentsDto getStudentByName(@PathVariable String name);

}
