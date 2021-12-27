package com.thingscloud.attributes;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AttributesService {

    final private AttributesRepository attributesRepository;

    public List<Attribute> getDeviceAttributes(UUID deviceId){

        return attributesRepository.findAllByDeviceId(deviceId);
    }

    public List<Attribute> postAttributes(String device_id, JsonNode payload){
        List<Attribute> attr =  createTimeseriesEntity(device_id, payload);
        return attributesRepository.saveAll(attr);
    }
    private List<Attribute> createTimeseriesEntity(String device_id, JsonNode payload) {
        long ts = System.currentTimeMillis();
        List<Attribute> attr = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> fields = payload.fields();

        while(fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode value = field.getValue();
            attr.add(new Attribute(key, value, ts, UUID.fromString(device_id)) );
        }

        return attr;
    }


    public void deleteDeviceAttributes(UUID device_id){

        attributesRepository.deleteByDeviceId(device_id);
    }


}
