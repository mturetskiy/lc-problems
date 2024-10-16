package com.mtur.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ParseString2 {
    private Map<String, ArrayList<String>> map = new HashMap<>();
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
            ArrayList<String> values = map.get(key);

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

}