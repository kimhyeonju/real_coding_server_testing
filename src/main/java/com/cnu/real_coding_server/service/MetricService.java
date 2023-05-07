package com.cnu.real_coding_server.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MetricService {
    private final MeterRegistry meterRegistry;
    private Counter counter;

    @PostConstruct
    public void init() {
        counter = meterRegistry.counter("api.call.count");
    }

    public double count() {
        counter.increment();
        return counter.count();
    }
}
