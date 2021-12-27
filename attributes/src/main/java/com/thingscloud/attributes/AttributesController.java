package com.thingscloud.attributes;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/attributes")
public class AttributesController {

    private final AttributesService attributesService;

    public AttributesController(AttributesService attributesService){
        this.attributesService = attributesService;
    }


    @GetMapping(value = "/{deviceId}")
    public ResponseEntity<List<Attribute>> getDeviceTelemetry(@PathVariable String deviceId){
        try {
            return ResponseEntity.ok(attributesService.getDeviceAttributes(UUID.fromString(deviceId)));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/{device_id}")
    @ResponseBody
    public ResponseEntity<List<Attribute>> postAttributes(@PathVariable String device_id, @RequestBody JsonNode payload){
        try {
            attributesService.postAttributes(device_id, payload);
            return  ResponseEntity.accepted().build();

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/{device_id}")
    private ResponseEntity<String> deleteDeviceTelemetry(@PathVariable String device_id){
        attributesService.deleteDeviceAttributes(UUID.fromString(device_id));
        return ResponseEntity.ok("Device Attributes Deleted");

    }





}
