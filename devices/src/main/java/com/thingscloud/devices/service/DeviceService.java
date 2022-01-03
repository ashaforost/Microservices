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

public interface DeviceService {

Optional<Device> getDeviceById(UUID id) ;

    List<Device> getDeviceByName(String name);
    List<Device> getAllDevices();
    List<Device> findUserDevices(UUID userId) throws IllegalArgumentException;
    UUID save(String name, UUID userId) throws  IllegalArgumentException;

    void deleteDeviceById(String device_id) ;


}