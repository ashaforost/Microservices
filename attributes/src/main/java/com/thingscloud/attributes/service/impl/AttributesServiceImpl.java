package com.thingscloud.attributes.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.thingscloud.attributes.repo.AttributesRepository;
import com.thingscloud.attributes.repo.model.Attribute;
import com.thingscloud.attributes.service.AttributesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
//@Transactional
public class AttributesServiceImpl implements AttributesService {


    final private AttributesRepository attributesRepository;
    private final RestTemplate restTemplate;
    public List<Attribute> getDeviceAttributes(UUID deviceId) {

        return attributesRepository.findAllByDeviceId(deviceId);
    }

    public List<Attribute> postAttributes(String device_id, JsonNode payload) {
        Boolean isDevice =  restTemplate.getForObject(
                "http://localhost:8092/device/exists/{id}",
                Boolean.class,
                device_id
        );
        if (isDevice) {

            List<Attribute> attr = createTimeseriesEntity(device_id, payload);
            return attributesRepository.saveAll(attr);
        }
        else throw new IllegalArgumentException();
    }



    private List<Attribute> createTimeseriesEntity(String device_id, JsonNode payload) {
        long ts = System.currentTimeMillis();
        List<Attribute> attr = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> fields = payload.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode value = field.getValue();
            attr.add(new Attribute(key, value, ts, UUID.fromString(device_id)));
        }

        return attr;
    }


    public void deleteDeviceAttributes(UUID device_id) {

        attributesRepository.deleteByDeviceId(device_id);
    }

}
