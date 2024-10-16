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

}