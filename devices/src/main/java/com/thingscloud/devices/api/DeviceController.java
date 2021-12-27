package com.thingscloud.devices.api;

import com.thingscloud.devices.api.dto.DeviceDto;
import com.thingscloud.devices.model.Device;
import com.thingscloud.devices.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @GetMapping()
    public ResponseEntity<List<Device>> getTenantDevices(){
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Device>> getDevice(@PathVariable String id){
        try {
            return ResponseEntity.ok(deviceService.getDeviceById(UUID.fromString(id)));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping()
    @ResponseBody
    public ResponseEntity<DeviceDto> saveDevice(@RequestBody DeviceDto device) {
        final String name = device.getName();
        final UUID user_id = device.getUserId();
        try {
            final UUID id = deviceService.save(name, user_id);
            final String location = String.format("/device/%s", id.toString());

            return ResponseEntity.created(URI.create(location)).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/v1/user/{userId}")
    private ResponseEntity<List<Device>> getUserDevices(@PathVariable String userId){
        try {

            List<Device> devices= deviceService.findUserDevices(UUID.fromString(userId));
            return ResponseEntity.ok(devices);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e ){
            return  ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping(value = "/{device_id}")
    public String deleteDevice(@PathVariable String device_id){
        try {
            deviceService.deleteDeviceById(UUID.fromString(device_id));
            return "Device deleted";
        } catch (Exception e){
            return "Device not found!";
        }

    }



}
