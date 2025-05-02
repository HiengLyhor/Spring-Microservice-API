package com.report_service.report.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("OPERATION")
public interface StudentOperationFeign {
}
