package dev.rexijie.pipeline.realtime.cleaning.service;

import java.util.Map;

public interface DataCleaner<D> {

    D cleanFromMap(Map<String, Object> source);

}
