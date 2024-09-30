package com.mtur.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ParseString2 {

    public void parse(String str) {
        // implement me

    }

    // No cache for original str
    public String getOriginal() {
        return null;
    }


    // O(1)
    public ??? getValues(String key) {
        return null;
    }

    public static void main(String[] args) {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val6;key1=val6";
        ParseString2 ps = new ParseString2();
        ps.parse(val);

        // ...
    }
}