package com.thingscloud.attributes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttributesRepository extends JpaRepository<Attribute, UUID>{

    List<Attribute> findAllByDeviceId(UUID device_id);

    void deleteAllByKeyAndDeviceId (String key, UUID deviceId);

    void deleteByDeviceId(UUID deviceId);

}
