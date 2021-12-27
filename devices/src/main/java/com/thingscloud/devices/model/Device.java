package com.thingscloud.devices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@EnableAutoConfiguration
@Builder

public class Device {

    @Id
    @Column(name = "device_id")
    private UUID id;


    @Column (name = "device_name")
    private String name;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "created_time")
    private Long createdTime;


    @Column(name = "user_id")
    private UUID userId;

    public Device(UUID id, String name, String accessToken, Long createdTime, UUID userId) {
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
        this.createdTime = createdTime;
        this.userId = userId;
    }


    public Device(){ }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }


}
