package com.mtur.other;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParseStringTemplate {

    public void parse(String str) {
        // implement me

    }

    // No cache for original str
    public String getOriginal() {
        return null;
    }


    // O(1)
    public Object getValues(String key) {
        return null;
    }

    public static void main(String[] args) {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val6;key1=val6";
        ParseStringTemplate ps = new ParseStringTemplate();
        ps.parse(val);

        // ...
    }
}