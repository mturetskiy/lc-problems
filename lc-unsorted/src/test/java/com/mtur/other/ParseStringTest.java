package com.mtur.other;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParseStringTest {
    @Test
    void testCorrectness() {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val5;key1=val6";
        ParseString parseString = new ParseString();
        parseString.parse(val);

        assertEquals(List.of("val3"), parseString.getValues("key3"));
        assertEquals(List.of("val1", "val4", "val6"), parseString.getValues("key1"));
        assertEquals(List.of("val2", "val5"), parseString.getValues("key2"));
        assertEquals(null, parseString.getValues("key5"));
    }

    @Test
    void testGetOriginal() {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val5;key1=val6";
        ParseString parseString = new ParseString();
        parseString.parse(val);

        assertEquals(val, parseString.getOriginal());
    }

    @Test
    void testGetOriginalDuplicateValues() {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val6;key1=val6";
        ParseString parseString = new ParseString();
        parseString.parse(val);

        assertEquals(val, parseString.getOriginal());
    }
}