package com.thingscloud.telemetry.repo;


import com.thingscloud.telemetry.repo.model.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, UUID> {

    List<Telemetry> findAllByDeviceId(UUID id);
    void deleteTelemetriesByDeviceId (UUID id);

}
