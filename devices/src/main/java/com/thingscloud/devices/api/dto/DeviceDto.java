package com.thingscloud.devices.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public final class DeviceDto {
    private UUID id;
    private String name;
    private String accessToken;
    private Long createdTime;
    private UUID userId;



    public DeviceDto(UUID id, String name, String accessToken, Long createdTime, UUID userId) {
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
        this.createdTime = createdTime;
        this.userId = userId;
    }
}
