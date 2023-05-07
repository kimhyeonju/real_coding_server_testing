package com.cnu.real_coding_server.controller;

import com.cnu.real_coding_server.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MetricController {

    private final MetricService metricService;

    @GetMapping("/api/count")
    public double count() {
        return metricService.count();
    }
}
