package com.base64jrp.reader;


import org.apache.commons.lang3.tuple.Pair;

/**
 * @author maximboguns
 */
public enum ReaderType {
    FILE, CONSOLE;

    public static Pair<ReaderType, String> valueOf(String inf, String inc) {
        if (inf != null) return Pair.of(FILE, inf);
        if (inc != null) return Pair.of(CONSOLE, inc);
        throw new IllegalArgumentException("Error, there is not such reader type");
    }
}
