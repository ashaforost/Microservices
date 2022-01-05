package com.thingscloud.attributes.repo;

import com.thingscloud.attributes.repo.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttributesRepository extends JpaRepository<Attribute, UUID>{

    List<Attribute> findAllByDeviceId(UUID device_id);

    void deleteAllByKeyAndDeviceId (String key, UUID deviceId);

    void deleteByDeviceId(UUID deviceId);

}
