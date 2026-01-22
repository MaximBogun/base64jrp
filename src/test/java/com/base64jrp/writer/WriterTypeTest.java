package com.base64jrp.writer;

import org.junit.Test;

import static org.junit.Assert.*;

public class WriterTypeTest {

    @Test
    public void shouldReturnConsoleTypeWhenOutcIsTrue() {
        // given
        boolean outc = true;
        boolean bufo = false;
        String outf = null;

        // when
        WriterType result = WriterType.valueOf(outc, bufo, outf);

        // then
        assertEquals(WriterType.CONSOLE, result);
    }

    @Test
    public void shouldReturnBufferTypeWhenBufoIsTrue() {
        // given
        boolean outc = false;
        boolean bufo = true;
        String outf = null;

        // when
        WriterType result = WriterType.valueOf(outc, bufo, outf);

        // then
        assertEquals(WriterType.BUFFER, result);
    }

    @Test
    public void shouldReturnFileTypeWhenOutfIsProvided() {
        // given
        boolean outc = false;
        boolean bufo = false;
        String outf = "/path/to/output.txt";

        // when
        WriterType result = WriterType.valueOf(outc, bufo, outf);

        // then
        assertEquals(WriterType.FILE, result);
    }

    @Test
    public void shouldPrioritizeConsoleOverBuffer() {
        // given - both outc and bufo are true
        boolean outc = true;
        boolean bufo = true;
        String outf = null;

        // when
        WriterType result = WriterType.valueOf(outc, bufo, outf);

        // then - console takes priority
        assertEquals(WriterType.CONSOLE, result);
    }

    @Test
    public void shouldPrioritizeConsoleOverFile() {
        // given - outc is true and outf is provided
        boolean outc = true;
        boolean bufo = false;
        String outf = "/path/to/output.txt";

        // when
        WriterType result = WriterType.valueOf(outc, bufo, outf);

        // then - console takes priority
        assertEquals(WriterType.CONSOLE, result);
    }

    @Test
    public void shouldPrioritizeBufferOverFile() {
        // given - bufo is true and outf is provided
        boolean outc = false;
        boolean bufo = true;
        String outf = "/path/to/output.txt";

        // when
        WriterType result = WriterType.valueOf(outc, bufo, outf);

        // then - buffer takes priority
        assertEquals(WriterType.BUFFER, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNoOutputProvided() {
        // given
        boolean outc = false;
        boolean bufo = false;
        String outf = null;

        // when
        WriterType.valueOf(outc, bufo, outf);

        // then - exception is expected
    }

    @Test
    public void shouldHaveCorrectEnumValues() {
        // given/when
        WriterType[] values = WriterType.values();

        // then
        assertEquals(3, values.length);
        assertEquals(WriterType.FILE, WriterType.valueOf("FILE"));
        assertEquals(WriterType.BUFFER, WriterType.valueOf("BUFFER"));
        assertEquals(WriterType.CONSOLE, WriterType.valueOf("CONSOLE"));
    }
}
