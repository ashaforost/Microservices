package com.thingscloud.telemetry;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TelemetryService {


    final private TelemetryRepository telemetryRepository;

    public List<Telemetry> getDeviceTelemetry(UUID device_id){
        return telemetryRepository.findAllByDeviceId(device_id);
    }

    public List<Telemetry> postTelemetry(String device_id, JsonNode payload){
        List<Telemetry> telemetry =  createTimeseriesEntity(device_id, payload);
        return telemetryRepository.saveAll(telemetry);
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
    public void deleteDeviceTelemetry(UUID deviceId){
        telemetryRepository.deleteByDeviceId(deviceId);
    }
    public List<Telemetry> getTelemetry(){
        return telemetryRepository.findAll();
   }
}
