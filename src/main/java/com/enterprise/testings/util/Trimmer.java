package com.enterprise.testings.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author max.gun
 * @since 12/09/2024
 */
public class Trimmer {

    // Via Jackson
    public static JsonNode trimJson(JsonNode root, int level) {
        if (level <= 0 || isNull(root) || root.isValueNode())
            return null;

        if (root.isObject()) {
            ObjectNode trimmedObj = new ObjectMapper().createObjectNode();
            root.fields().forEachRemaining(jsonEntry -> {
                JsonNode child = trimJson(jsonEntry.getValue(), level - 1);
                if (nonNull(child)) {
                    trimmedObj.set(jsonEntry.getKey(), child);
                }
            });
            return trimmedObj;

        } else if (root.isArray()) {
            ArrayNode trimmedArr = new ObjectMapper().createArrayNode();
            root.forEach(element ->{
                JsonNode child = trimJson(element, level - 1);
                if (nonNull(child)) {
                    trimmedArr.add(child);
                }
            });
            return trimmedArr;
        }

        return root;
    }

    // Via Gson
    public static JsonElement trimJson(JsonElement root, int level) {
        if (level <= 0 || isNull(root) || root.isJsonPrimitive())
            return null;

        if (root.isJsonObject()) {
            JsonObject trimmedObj = new JsonObject();
            root.getAsJsonObject().entrySet().forEach(entry -> {
                JsonElement child = trimJson(entry.getValue(), level - 1);
                if (nonNull(child)) {
                    trimmedObj.add(entry.getKey(), child);
                }
            });
            return trimmedObj;

        } else if (root.isJsonArray()) {
            JsonArray trimmedArr = new JsonArray();
            root.getAsJsonArray().forEach(element -> {
                JsonElement child = trimJson(element, level - 1);
                if (nonNull(child)) {
                    trimmedArr.add(child);
                }
            });
            return trimmedArr;
        }

        return root;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String jsonString = "{\"name\":\"John\",\"address\":{\"city\":\"New York\",\"zip\":\"10001\",\"coordinates\":{\"lat\":40.7128,\"lon\":-74.0060}}, \"phones\":[{\"type\":\"mobile\", \"number\":\"1234567890\"}]}";
        int level = 1;

        System.out.println("Jackson Result:");
        testJacksonTrim(jsonString, level);
        System.out.println("Gson Result:");
        testGsonTrim(jsonString, level);
    }

    public static void testJacksonTrim(String jsonString, int level) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode trimmedJson = trimJson(rootNode, level);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(trimmedJson));
    }

    public static void testGsonTrim(String jsonString, int level) {
        JsonElement rootElement = JsonParser.parseString(jsonString);
        JsonElement trimmedJson = trimJson(rootElement, level);
        System.out.println(trimmedJson.toString());
    }
}
