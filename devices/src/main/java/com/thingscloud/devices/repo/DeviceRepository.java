package com.thingscloud.devices.repo;

import com.thingscloud.devices.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository  extends JpaRepository<Device, UUID> {
////    List<Device> getAllByUser_id (UUID id);
//
//    Device getById(UUID id);
//    Optional<Device> findDeviceByName(String name);
    List<Device> findByName(String name);
    long countByUserId(UUID user_id);
    List<Device> findByUserId(UUID userId);
    void deleteById (UUID deviceId);

}
