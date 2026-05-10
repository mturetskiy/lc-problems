package com.mtur.other;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ParseString4 {
    private Map<String, List<String>> keyToValues = new HashMap<>();
    private List<String> keysInOrder;

    public void parse(String str) {
        keysInOrder = new LinkedList<>();

        String[] keyValuePairs = str.split(";");
        for (String kvPair : keyValuePairs) {
            String[] kv = kvPair.split("=");
            String key = kv[0];
            String val = kv[1];

            List<String> keyValues = keyToValues.computeIfAbsent(key, k -> new ArrayList<>());
            keyValues.add(val);
            keysInOrder.add(key);
        }

    }

    // No cache for original str
    public String getOriginal() {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> currentKeyValueIndex = new HashMap<>(keysInOrder.size());

        for (int i = 0; i < keysInOrder.size(); i++) {
            String key = keysInOrder.get(i);

            Integer currentValIndex = currentKeyValueIndex.getOrDefault(key, 0);
            List<String> values = keyToValues.get(key);
            String value = values.get(currentValIndex);

            currentKeyValueIndex.put(key, ++currentValIndex);

            sb.append(key + "=" + value);
            if (i < keysInOrder.size() - 1) {
                sb.append(";");
            }
        }

        return sb.toString();
    }


    // O(1)
    public List<String> getValues(String key) {
        return keyToValues.getOrDefault(key, Collections.emptyList());
    }

    public static void main(String[] args) {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val6;key1=val6";
        ParseString4 ps = new ParseString4();
        ps.parse(val);

        log.info("{}", ps.getValues("key1"));
        log.info("{}", ps.getValues("key2"));
        log.info("{}", ps.getValues("key3"));

        log.info("Restored original: {}", ps.getOriginal());
        log.info("Is same: {}", val.equals(ps.getOriginal()));


    }
}