package no.netcompany.sommerjobb2017.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import spark.ResponseTransformer;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Service
public class JsonUtil {
    private final ObjectMapper objectMapper;

    public JsonUtil(final ObjectMapper objectMapper) {
        Objects.requireNonNull(objectMapper);
        this.objectMapper = objectMapper;
    }

    public <T> T fromJson(final String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public ResponseTransformer toJson() {
        return objectMapper::writeValueAsString;
    }
}
