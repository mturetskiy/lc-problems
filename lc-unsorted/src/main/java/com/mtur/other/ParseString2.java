package com.mtur.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ParseString2 {
    private Map<String, List<String>> map = new HashMap<>();
    private List<String> keys = new ArrayList<>();

    public void parse(String str) {
        if (StringUtils.isEmpty(str)) {
            return;
        }

        String[] pairs = str.split(";");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            if (kv.length != 2) {
                throw new IllegalArgumentException("Bad input!");
            }

            String key = kv[0];
            String value = kv[1];

            keys.add(key);

            List<String> values = map.computeIfAbsent(key, k -> new ArrayList<>());
            values.add(value);

        }
    }

    public String getOriginal() {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> nextKeyValIndex = new HashMap<>();

        for (String key : keys) {
            List<String> values = map.get(key);

            Integer valueIdx = nextKeyValIndex.getOrDefault(key, 0);
            String value = values.get(valueIdx);
            sb.append(key).append("=").append(value).append(";");

            nextKeyValIndex.put(key, ++valueIdx);
        }

        return sb.substring(0, sb.length() - 1);
    }

    public List<String> getValues(String key) {
        return map.get(key);
    }

    public static void main(String[] args) {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val6;key1=val6";
        ParseString2 ps = new ParseString2();
        ps.parse(val);

        log.info("{}", ps.getValues("key1"));
        log.info("{}", ps.getValues("key2"));
        log.info("{}", ps.getValues("key3"));

        log.info("Restored original: {}", ps.getOriginal());
        log.info("Is same: {}", val.equals(ps.getOriginal()));


    }

}