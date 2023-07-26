package com.messaging.todolist.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static Map<String, Object> convertStringToMap(String json) {
        Map<String, Object> map = null;
        try {
            ObjectMapper mapper = new ObjectMapper();

            map = mapper.readValue(json,
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (Exception e) {
            logger.error("Error converting json to Map ", e);
        }
        return map;
    }
}
