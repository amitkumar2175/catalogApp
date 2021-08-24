package com.demo.catalogApp;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = createObjectMapper();
        OBJECT_MAPPER.configure(Feature.AUTO_CLOSE_SOURCE, false);
    }

    private JsonUtils() {}

    /** Creates an {@link ObjectMapper} with configuration to handle Java 8 {@link Optional} correctly */
    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        return objectMapper;
    }

    public static <T> String serializeObject(T object) {
        return serializeObject(object,
                ioe -> new UncheckedIOException(String.format("Failed to convert %s to JSON string", object.getClass().getName()), ioe));
    }

    public static <T> String serializeObject(T object, Function<IOException, RuntimeException> exceptionHandler) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw exceptionHandler.apply(e);
        }
    }

    public static <T> T deserializeObject(String jsonString, Class<T> valueType) {
        return deserializeObject(jsonString, valueType,
                ioe -> new UncheckedIOException(String.format("Failed to convert '%s' to %s", jsonString, valueType.getName()), ioe));
    }

    public static <T> T deserializeObject(String jsonString, Class<T> valueType, Function<IOException, RuntimeException> exceptionHandler) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, valueType);
        } catch (IOException e) {
            throw exceptionHandler.apply(e);
        }
    }

}
