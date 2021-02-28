package com.base64jrp.writer;

/**
 * @author maximboguns
 */
public enum WriterType {
    FILE, BUFFER, CONSOLE;
    public static WriterType valueOf(boolean outc, boolean bufo, String outf) {
        if (outc) return CONSOLE;
        if (bufo) return BUFFER;
        if (outf != null) return FILE;
        throw new IllegalArgumentException("Error, there is not such writer type");
    }
}
