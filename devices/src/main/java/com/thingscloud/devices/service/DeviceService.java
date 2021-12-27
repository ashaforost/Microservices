package com.thingscloud.devices.service;

import com.thingscloud.devices.model.Device;
import com.thingscloud.devices.model.User;
import com.thingscloud.devices.repo.DeviceRepository;

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
@Transactional
@AllArgsConstructor
public class DeviceService {

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


    public UUID save(String name, UUID userId) throws  IllegalArgumentException {
        User user = restTemplate.getForObject(
                "http://users:8091/users/id=/{id}",
                User.class,
                userId
        );

        int deviceLimit = user.getDeviceLimit();
        long deviceCount  = deviceRepository.countByUserId(userId);

        if ( deviceCount == deviceLimit){
            throw new IllegalArgumentException("Device limit reached");
        }

        long createdTime = Instant.now().toEpochMilli();
        UUID id = UUID.randomUUID();
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        final Device device = new Device(id, name, token, createdTime, userId );
        final Device savedDevice = deviceRepository.save(device);
        return savedDevice.getId();

    }

    public void deleteDeviceById(UUID device_id) {

        deviceRepository.deleteById(device_id);
        restTemplate.delete(
                "http://telemetry:8093/api/telemetry/{device_id}",
                String.class,
                device_id.toString()
        );


        restTemplate.delete(
                "http://attributes:8094/api/telemetry/{device_id}",
                String.class,
                device_id.toString()
        );


    }

}