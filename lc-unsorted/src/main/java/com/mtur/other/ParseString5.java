package com.mtur.other;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ParseString5 {
    private Map<String, List<String>> keyToValues = new LinkedHashMap<>();

    public void parse(String str) {
        String[] keyValuePairs = str.split(";");
        for (String kvPair : keyValuePairs) {
            String[] kv = kvPair.split("=");
            String key = kv[0];
            String val = kv[1];

            List<String> keyValues = keyToValues.computeIfAbsent(key, k -> new ArrayList<>());
            keyValues.add(val);
        }

    }

    // No cache for original str
    public String getOriginal() {
        StringBuilder sb = new StringBuilder();

        keyToValues.keySet().forEach(k-> {
            sb.append(k).append(";");
            List<String> strings = keyToValues.get(k);
            strings.forEach(v -> {
                sb.append(v).append(", ");
            });
        });

        return sb.toString();
    }


    // O(1)
    public List<String> getValues(String key) {
        return keyToValues.getOrDefault(key, Collections.emptyList());
    }

    public static void main(String[] args) {
        String val = "key3=val3;key1=val1;key2=val2;key1=val4;key2=val6;key1=val6";
        ParseString5 ps = new ParseString5();
        ps.parse(val);

        log.info("{}", ps.getValues("key1"));
        log.info("{}", ps.getValues("key2"));
        log.info("{}", ps.getValues("key3"));

        log.info("Restored original: {}", ps.getOriginal());
        log.info("Is same: {}", val.equals(ps.getOriginal()));


    }
}