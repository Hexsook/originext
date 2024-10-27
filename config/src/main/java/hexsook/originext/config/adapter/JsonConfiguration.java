package hexsook.originext.config.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import hexsook.originext.config.BaseConfiguration;
import hexsook.originext.config.ConfigurationAdapter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Json config file adapter.
 */
public class JsonConfiguration extends ConfigurationAdapter {

    public static final JsonConfiguration INSTANCE = new JsonConfiguration();

    private final Gson json = new GsonBuilder().serializeNulls().setPrettyPrinting()
            .registerTypeAdapter(BaseConfiguration.class, (JsonSerializer<BaseConfiguration>) (src, typeOfSrc, context) -> {
                JsonObject jsonObject = new JsonObject();
                src.getMap().forEach((key, value) -> {
                    if (value instanceof Map<?, ?>) {
                        jsonObject.add(key, context.serialize(new BaseConfiguration((Map<?, ?>) value)));
                    } else {
                        jsonObject.add(key, context.serialize(value));
                    }
                });
                return jsonObject;
            }).create();

    private JsonConfiguration() {
    }

    @Override
    public BaseConfiguration load(File file) throws Exception {
        try (FileInputStream in = new FileInputStream(file)) {
            return load(in);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public BaseConfiguration load(InputStream in) {
        InputStreamReader reader = new InputStreamReader(in);
        Map<String, Object> map = json.fromJson(reader, LinkedHashMap.class);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return new BaseConfiguration(map);
    }

    @Override
    public void save(BaseConfiguration config, File file) throws IOException {
        try (Writer writer = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8)) {
            save(config, writer);
        }
    }

    @Override
    public void save(BaseConfiguration config, Writer writer) {
        json.toJson(config.getMap(), writer);
    }
}
