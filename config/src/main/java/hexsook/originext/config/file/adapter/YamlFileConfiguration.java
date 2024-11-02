package hexsook.originext.config.file.adapter;

import hexsook.originext.config.Configuration;
import hexsook.originext.config.file.FileConfigurationAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snakeyaml.engine.v2.api.*;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.nodes.Tag;
import org.snakeyaml.engine.v2.representer.StandardRepresenter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Yaml config file adapter.
 */
public class YamlFileConfiguration extends FileConfigurationAdapter {

    public static final YamlFileConfiguration INSTANCE = new YamlFileConfiguration();

    private static final Logger logger = LoggerFactory.getLogger(YamlFileConfiguration.class);

    private final Supplier<Dump> yamlDumperSupplier = () -> {
        DumpSettings settings = DumpSettings.builder()
                .setDefaultFlowStyle(FlowStyle.BLOCK)
                .build();
        return new Dump(settings, new Representer(settings));
    };

    private final Supplier<Load> yamlLoaderSupplier = () -> {
        LoadSettings settings = LoadSettings.builder().build();
        return new Load(settings);
    };

    private YamlFileConfiguration() {
    }

    public Dump getYamlDumper() {
        return yamlDumperSupplier.get();
    }

    public Load getYamlLoader() {
        return yamlLoaderSupplier.get();
    }

    @Override
    public Configuration load(File file) throws Exception {
        try (FileInputStream in = new FileInputStream(file)) {
            return load(in);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Configuration load(InputStream in) {
        Map<String, Object> map = (Map<String, Object>) getYamlLoader().loadFromInputStream(in);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return new Configuration(map);
    }

    @Override
    public void save(Configuration config, File file) throws IOException {
        try (Writer writer = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8)) {
            save(config, writer);
        }
    }

    @Override
    public void save(Configuration config, Writer writer) {
        getYamlDumper().dump(config.getMap(), new StreamDataWriter() {
            @Override
            public void write(String str) {
                try {
                    writer.write(str);
                } catch (IOException e) {
                    logger.error("Error while writing context to file");
                }
            }

            @Override
            public void write(String str, int off, int len) {
                try {
                    writer.write(str, off, len);
                } catch (IOException e) {
                    logger.error("Error while writing context to file");
                }
            }

            @Override
            public void flush() {
                try {
                    writer.flush();
                } catch (IOException e) {
                    logger.error("Error while flushing writer");
                }
            }
        });
    }

    private static class Representer extends StandardRepresenter {
        public Representer(DumpSettings settings) {
            super(settings);
            this.representers.put(Configuration.class, data -> {
                Configuration config = (Configuration) data;
                return representMapping(Tag.MAP, config.getMap(), FlowStyle.BLOCK);
            });
        }
    }
}
