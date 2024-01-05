package com.mtur.lc.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JmhUtils {
    public static void runJmh() {
        try {
            org.openjdk.jmh.Main.main(new String[]{"-i", "100", "-f", "1", "-wi", "2", "-bm", "SingleShotTime", "-tu", "ms"});
        } catch (Exception e) {
            log.error("Exception during jmh run.", e);
        }
    }

    public static void runJmhNoDefaults() {
        try {
            org.openjdk.jmh.Main.main(new String[]{});
        } catch (Exception e) {
            log.error("Exception during jmh run.", e);
        }
    }
}