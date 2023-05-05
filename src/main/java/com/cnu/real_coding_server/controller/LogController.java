package com.cnu.real_coding_server.controller;

import com.cnu.real_coding_server.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping("/log")
    public String getLog() {
        logService.log();
        return "console log";
    }
}
