package com.thingscloud.telemetry.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.thingscloud.telemetry.repo.model.Telemetry;
import com.thingscloud.telemetry.repo.TelemetryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;


public interface TelemetryService {

    List<Telemetry> getDeviceTelemetry(String device_id) throws IllegalArgumentException;

    List<Telemetry> postTelemetry(String device_id, JsonNode payload) throws IllegalArgumentException;
     void deleteDeviceTelemetry(String deviceId);

}
