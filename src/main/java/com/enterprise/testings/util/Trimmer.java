package com.enterprise.testings.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonElement;

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
                    }
            );
            return trimmedObj;

        } else if (root.isArray()) {
            ArrayNode trimmedArr = new ObjectMapper().createArrayNode();
            root.forEach(element ->{
                        JsonNode child = trimJson(element, level - 1);
                        if (nonNull(child)) {
                            trimmedArr.add(child);
                        }
                    }
            );
            return trimmedArr;
        }

        return root;
    }

    // Via Gson
    public static JsonElement trimJson(JsonElement root, int level) {

    }
}
