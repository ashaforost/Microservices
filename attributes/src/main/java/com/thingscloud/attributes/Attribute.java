package com.thingscloud.attributes;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.UUID;
@EnableAutoConfiguration
@Entity
@Table(name = "attributes")
@DynamicUpdate
@NoArgsConstructor
public class Attribute {


    @Id
    @JsonProperty( value = "entity_id", access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "entity_id")
    private UUID id;

    @Column(name="key")
    private String key;

    @Column(name = "str_value")
    private String strValue;

    @Column(name = "long_value")

    private Long longValue;

    @Column(name = "created_time")
    private Long created_time;

    private UUID deviceId;
    public Attribute(String key, JsonNode value, long ts, UUID deviceId) {
        this.key = key;
        this.created_time = ts;
        this.deviceId = deviceId;
        this.id = UUID.randomUUID();

        if (value.isNumber()) {
            this.longValue = value.longValue();
        } else {
            this.strValue = value.toString().replace("\"", "");
        }

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Long created_time) {
        this.created_time = created_time;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }
}
