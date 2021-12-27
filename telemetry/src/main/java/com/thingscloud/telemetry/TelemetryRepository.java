package com.thingscloud.telemetry;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, UUID> {

    List<Telemetry> findAllByDeviceId(UUID id);

    void deleteByKey(String key);

    void deleteByDeviceId(UUID id);
//    void deleteAllByDeviceIdAnd;Key(UUID deviceId, String key);

}
