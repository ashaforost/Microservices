package com.thingscloud.telemetry;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/telemetry")
public class TelemetryController {


    private TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService){  this.telemetryService = telemetryService;}

    @GetMapping(value = "/{deviceId}")
    public ResponseEntity<List<Telemetry>> getDeviceTelemetry(@PathVariable String deviceId){

        try {
            return ResponseEntity.ok(telemetryService.getDeviceTelemetry(UUID.fromString(deviceId)));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping()
    public ResponseEntity<List<Telemetry>> getDeviceTelemetry(){
        try {
            return ResponseEntity.ok(telemetryService.getTelemetry());
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/{device_id}")
    @ResponseBody
    public ResponseEntity<List<Telemetry>> postTelemetry(@PathVariable String device_id, @RequestBody JsonNode payload){
        try {
            return  ResponseEntity.ok(telemetryService.postTelemetry(device_id , payload));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/{device_id}")
    private ResponseEntity<String> deleteDeviceTelemetry(@PathVariable String device_id){
            telemetryService.deleteDeviceTelemetry(UUID.fromString(device_id));
            return ResponseEntity.ok("Device Telemetry Deleted");

    }


}
