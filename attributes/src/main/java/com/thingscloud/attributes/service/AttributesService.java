package com.thingscloud.attributes.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.thingscloud.attributes.repo.model.Attribute;
import com.thingscloud.attributes.repo.AttributesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

public interface AttributesService {
List<Attribute> getDeviceAttributes(UUID deviceId) throws IllegalArgumentException;
List<Attribute> postAttributes(String device_id, JsonNode payload) throws IllegalArgumentException;
void deleteDeviceAttributes(UUID device_id);




}
