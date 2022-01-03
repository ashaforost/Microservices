package com.thingscloud.telemetry.api;


import com.fasterxml.jackson.databind.JsonNode;
import com.thingscloud.telemetry.repo.model.Telemetry;
import com.thingscloud.telemetry.service.impl.TelemetryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/telemetry")
@RequiredArgsConstructor
public class TelemetryController {


    private final TelemetryServiceImpl telemetryServiceImpl;

    @GetMapping(value = "/{deviceId}")
    public ResponseEntity<List<Telemetry>> getDeviceTelemetry(@PathVariable String deviceId){

        try {
            return ResponseEntity.ok(telemetryServiceImpl.getDeviceTelemetry(deviceId));
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping(value = "/{device_id}")
    @ResponseBody
    public ResponseEntity<List<Telemetry>> postTelemetry(@PathVariable String device_id, @RequestBody JsonNode payload){
        try {
            return  ResponseEntity.ok(telemetryServiceImpl.postTelemetry(device_id , payload));
        }catch (IllegalArgumentException e){
            return  ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping(value = "/{device_id}")
    private ResponseEntity<Void> deleteDeviceTelemetry(@PathVariable String device_id){
            telemetryServiceImpl.deleteDeviceTelemetry(device_id);
        return ResponseEntity.noContent().build();
    }


}
