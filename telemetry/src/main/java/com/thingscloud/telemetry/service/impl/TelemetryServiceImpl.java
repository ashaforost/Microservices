package com.thingscloud.telemetry.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.thingscloud.telemetry.repo.model.Telemetry;
import com.thingscloud.telemetry.repo.TelemetryRepository;
import com.thingscloud.telemetry.service.TelemetryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
//@Transactional
public class TelemetryServiceImpl implements TelemetryService {


    final private TelemetryRepository telemetryRepository;
    private final RestTemplate restTemplate;

    private Boolean checkDevice(String device_id){

        Boolean isDevice = restTemplate.getForObject(
                "http://devices:8092/device/exists/{id}",
                Boolean.class,
                device_id
        );
        return isDevice;
    }

    public List<Telemetry> getDeviceTelemetry(String device_id){
        if( checkDevice(device_id)) {
            return telemetryRepository.findAllByDeviceId(UUID.fromString(device_id));
        }
        else throw new IllegalArgumentException();
    }

    public List<Telemetry> postTelemetry(String device_id, JsonNode payload){

        if (checkDevice(device_id)) {
            List<Telemetry> telemetry = createTimeseriesEntity(device_id, payload);
            return telemetryRepository.saveAll(telemetry);
        }
        else throw new IllegalArgumentException();
    }

    private  List<Telemetry> createTimeseriesEntity(String device_id, JsonNode payload) {
        long ts = System.currentTimeMillis();
        List<Telemetry> attr = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> fields = payload.fields();

        while(fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode value = field.getValue();
            attr.add(new Telemetry(key, value, ts, UUID.fromString(device_id)) );
        }

        return attr;
    }
    public void deleteDeviceTelemetry(String deviceId){
        telemetryRepository.deleteTelemetriesByDeviceId(UUID.fromString(deviceId));
    }

}
