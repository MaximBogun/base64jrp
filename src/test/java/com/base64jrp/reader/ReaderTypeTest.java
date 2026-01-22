package com.base64jrp.reader;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReaderTypeTest {

    @Test
    public void shouldReturnFileTypeWhenInfIsProvided() {
        // given
        String inf = "/path/to/file.txt";
        String inc = null;

        // when
        Pair<ReaderType, String> result = ReaderType.valueOf(inf, inc);

        // then
        assertEquals(ReaderType.FILE, result.getKey());
        assertEquals(inf, result.getValue());
    }

    @Test
    public void shouldReturnConsoleTypeWhenIncIsProvided() {
        // given
        String inf = null;
        String inc = "console input";

        // when
        Pair<ReaderType, String> result = ReaderType.valueOf(inf, inc);

        // then
        assertEquals(ReaderType.CONSOLE, result.getKey());
        assertEquals(inc, result.getValue());
    }

    @Test
    public void shouldPrioritizeFileOverConsole() {
        // given - both are provided
        String inf = "/path/to/file.txt";
        String inc = "console input";

        // when
        Pair<ReaderType, String> result = ReaderType.valueOf(inf, inc);

        // then - file takes priority
        assertEquals(ReaderType.FILE, result.getKey());
        assertEquals(inf, result.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenBothAreNull() {
        // given
        String inf = null;
        String inc = null;

        // when
        ReaderType.valueOf(inf, inc);

        // then - exception is expected
    }

    @Test
    public void shouldHaveCorrectEnumValues() {
        // given/when
        ReaderType[] values = ReaderType.values();

        // then
        assertEquals(2, values.length);
        assertEquals(ReaderType.FILE, ReaderType.valueOf("FILE"));
        assertEquals(ReaderType.CONSOLE, ReaderType.valueOf("CONSOLE"));
    }
}
