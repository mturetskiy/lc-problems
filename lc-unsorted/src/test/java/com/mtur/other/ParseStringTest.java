package com.mtur.other;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParseStringTest {
    private ParseString2 parser;

    @BeforeEach
    void setUp() {
        parser = new ParseString2();
    }

    @Test
    void testCorrectness() {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val5;key1=val6";

        parser.parse(val);

        assertArrayEquals(List.of("val3").toArray(), parser.getValues("key3").toArray());
        assertArrayEquals(List.of("val1", "val4", "val6").toArray(), parser.getValues("key1").toArray());
        assertArrayEquals(List.of("val2", "val5").toArray(), parser.getValues("key2").toArray());
        assertEquals(null, parser.getValues("key5"));
    }

    @Test
    void testGetOriginal() {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val5;key1=val6";
        parser.parse(val);

        assertEquals(val, parser.getOriginal());
    }

    @Test
    void testGetOriginalDuplicateValues() {
        String val = "key1=val1;key2=val2;key3=val3;key1=val4;key2=val6;key1=val6";
        parser.parse(val);

        assertEquals(val, parser.getOriginal());
    }
}