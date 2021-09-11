package dev.rexijie.pipeline.realtime.cleaning.service;

import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.avro.AvroSchemaProvider;
import org.apache.avro.data.Json;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.stream.Collectors;

public class AvroUtils {
    private static final Logger LOG = LoggerFactory.getLogger(AvroUtils.class);
    private final static String SCHEMA_DIR = System.getenv("SCHEMA_DIR");


    public static void loadData() {
        Objects.requireNonNull(SCHEMA_DIR, "Schema directory not bound");
            try {
                File file = ResourceUtils.getFile(SCHEMA_DIR);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String schema = bufferedReader.lines().collect(Collectors.joining());
                AvroSchema avroSchema = new AvroSchema(schema);
                GenericRecord gr = new GenericData.Record(avroSchema.rawSchema());
                gr.put("id", 10L);
                gr.put("sale", 10L);

//                gr.put("name", "Rex");
                LOG.info(gr.toString());
                System.exit(0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }
}
