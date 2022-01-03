package com.thingscloud.attributes.api;


import com.fasterxml.jackson.databind.JsonNode;
import com.thingscloud.attributes.repo.model.Attribute;
import com.thingscloud.attributes.service.AttributesService;
import com.thingscloud.attributes.service.impl.AttributesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/attributes")
@RequiredArgsConstructor
public class AttributesController {

    private final AttributesServiceImpl attributesServiceImpl;


    @GetMapping(value = "/{deviceId}")
    public ResponseEntity<List<Attribute>> getDeviceAttributes(@PathVariable String deviceId){
        try {
            return ResponseEntity.ok(attributesServiceImpl.getDeviceAttributes(UUID.fromString(deviceId)));
        }catch (IllegalArgumentException e){
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/{device_id}")
    @ResponseBody
    public ResponseEntity<List<Attribute>> postAttributes(@PathVariable String device_id, @RequestBody JsonNode payload){
        try {
            attributesServiceImpl.postAttributes(device_id, payload);
            return  ResponseEntity.accepted().build();

        }catch (IllegalArgumentException e){
            return  ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping(value = "/{device_id}")
    private ResponseEntity<Void> deleteDeviceAttributes(@PathVariable String device_id){
        attributesServiceImpl.deleteDeviceAttributes(UUID.fromString(device_id));
        return ResponseEntity.noContent().build();
    }





}
