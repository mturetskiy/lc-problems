package com.mtur.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ParseString {
    private Map<String, List<String>> map = new HashMap<>();
    private Map<Pair<String,String>, Integer> keyValuePos = new HashMap<>();

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

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            keyValuePos.put(new ImmutablePair<>(key,value), pos);
            pos++;
        }

    }

    public String getOriginal() {
        String[] res = new String[keyValuePos.size()];

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (String value: values) {
                Integer pos = keyValuePos.get(new ImmutablePair<>(key,value));
                res[pos] = key + "=" + value;
            }
        }

        return StringUtils.join(res,";");
    }

    public List<String> getValues(String key) {
        return map.get(key);
    }

}