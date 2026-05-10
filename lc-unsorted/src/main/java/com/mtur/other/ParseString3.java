package com.mtur.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Slf4j
public class ParseString3 {
    private Map<String, Map<Integer, String>> map = new HashMap<>();
    private int pairsCount = 0;

    public void parse(String str) {
        if (StringUtils.isEmpty(str)) {
            return;
        }

        String[] pairs = str.split(";");
        int pos = 0;
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            if (kv.length != 2) {
                throw new IllegalArgumentException("Bad input!");
            }

            String key = kv[0];
            String value = kv[1];

            Map<Integer, String> posValueMap = map.computeIfAbsent(key, k -> new HashMap<>());
            posValueMap.put(pos, value);
            pos++;
        }

        pairsCount = pos;

    }

    public String getOriginal() {
        String[] res = new String[pairsCount];

        for (Map.Entry<String, Map<Integer, String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Map<Integer, String> posValueMap = entry.getValue();

            posValueMap.forEach((pos, val) -> res[pos] = key + "=" + val);
        }

        return StringUtils.join(res,";");
    }

    public Collection<String> getValues(String key) {
        Map<Integer, String> valPosMap = map.get(key);
        return valPosMap != null ? valPosMap.values() : null;
    }

    public static void main(String[] args) {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val6;key1=val6";
        ParseString3 ps = new ParseString3();
        ps.parse(val);

        log.info("{}", ps.getValues("key1"));
        log.info("{}", ps.getValues("key2"));
        log.info("{}", ps.getValues("key3"));

        log.info("Restored original: {}", ps.getOriginal());
        log.info("Is same: {}", val.equals(ps.getOriginal()));


    }
}