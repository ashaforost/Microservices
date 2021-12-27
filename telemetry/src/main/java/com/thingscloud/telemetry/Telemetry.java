package com.thingscloud.telemetry;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import javax.persistence.*;
import java.util.UUID;
@EnableAutoConfiguration
@Entity
@Table(name = "telemetry")
public class Telemetry {

    public Telemetry(){
    }

    @Id
    @JsonProperty( value = "entity_id", access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "entity_id")
    public UUID id;

    @Column(name="key")
    public String key;

    @Column(name = "str_value")
    public String str_value;

    @Column(name = "long_value")

    public Long long_value;

    @Column(name = "ts")
    public Long ts;

    @Column(name = "device_id")
    private UUID deviceId;


    public Telemetry(String key, JsonNode value, long ts, UUID deviceId) {

            this.key = key;
            this.ts = ts;
            this.deviceId = deviceId;
            this.id = UUID.randomUUID();

            if (value.isNumber()) {
                this.long_value = value.longValue();
            } else {
                this.str_value = value.toString().replace("\"", "");
            }
    }

}



