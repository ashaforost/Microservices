package com.thingscloud.devices.service.impl;

import com.thingscloud.devices.model.Device;
import com.thingscloud.devices.model.User;
import com.thingscloud.devices.repo.DeviceRepository;
import com.thingscloud.devices.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
//@Transactional
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {


    private final DeviceRepository deviceRepository;
    private final RestTemplate restTemplate;


    public Optional<Device> getDeviceById(UUID id) {
        return deviceRepository.findById(id);
    }

    public List<Device> getDeviceByName(String name) {
        return deviceRepository.findByName(name);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public List<Device> findUserDevices(UUID userId) throws IllegalArgumentException {
        return deviceRepository.findByUserId(userId);
    }


    public UUID save(String name, UUID userId) {
        User user = restTemplate.getForObject(
                "http://users:8091/users/id=/{id}",
                User.class,
                userId
        );
        if (user == null ) throw new IllegalArgumentException("User Not Found!");
        int deviceLimit = user.getDeviceLimit();
        long deviceCount  = deviceRepository.countByUserId(userId);

        if ( deviceCount == deviceLimit){
            throw new IllegalArgumentException("Device limit reached!");
        }

        long createdTime = Instant.now().toEpochMilli();
        UUID id = UUID.randomUUID();
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        final Device device = new Device(id, name, token, createdTime, userId );
        try{
            final Device savedDevice = deviceRepository.save(device);
            return savedDevice.getId();
        }catch (Exception e){
            throw new IllegalArgumentException();
        }

    }

    public void deleteDeviceById(String device_id) {

        deviceRepository.deleteById(UUID.fromString(device_id));
        restTemplate.delete(
                "http://telemetry:8093/api/telemetry/{device_id}",
                device_id
        );


        restTemplate.delete(
                "http://attributes:8094/api/attributes/{device_id}",
                device_id
        );


    }
}
