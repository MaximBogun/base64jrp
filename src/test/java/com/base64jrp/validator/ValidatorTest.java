package com.base64jrp.validator;

import com.base64jrp.shell.ShellConsole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {

    @Mock
    private ShellConsole shellConsole;

    @Test
    public void shouldReturnTrueWhenFileInputAndConsoleOutput() {
        // given
        Validator validator = new Validator(shellConsole);

        // when
        boolean result = validator.isValidArgsEnc("/path/to/file", null, true, false, null);

        // then
        assertTrue(result);
        verify(shellConsole, never()).printInfo(anyString());
    }

    @Test
    public void shouldReturnTrueWhenConsoleInputAndBufferOutput() {
        // given
        Validator validator = new Validator(shellConsole);

        // when
        boolean result = validator.isValidArgsEnc(null, "some input", false, true, null);

        // then
        assertTrue(result);
        verify(shellConsole, never()).printInfo(anyString());
    }

    @Test
    public void shouldReturnTrueWhenFileInputAndFileOutput() {
        // given
        Validator validator = new Validator(shellConsole);

        // when
        boolean result = validator.isValidArgsEnc("/path/to/input", null, false, false, "/path/to/output");

        // then
        assertTrue(result);
        verify(shellConsole, never()).printInfo(anyString());
    }

    @Test
    public void shouldReturnFalseWhenNoInputProvided() {
        // given
        Validator validator = new Validator(shellConsole);

        // when
        boolean result = validator.isValidArgsEnc(null, null, true, false, null);

        // then
        assertFalse(result);
        verify(shellConsole).printInfo("Required input, type command with --inf ${path} or --inc ${input}.");
    }

    @Test
    public void shouldReturnFalseWhenEmptyInputProvided() {
        // given
        Validator validator = new Validator(shellConsole);

        // when
        boolean result = validator.isValidArgsEnc("", "", true, false, null);

        // then
        assertFalse(result);
        verify(shellConsole).printInfo("Required input, type command with --inf ${path} or --inc ${input}.");
    }

    @Test
    public void shouldReturnFalseWhenBlankInputProvided() {
        // given
        Validator validator = new Validator(shellConsole);

        // when
        boolean result = validator.isValidArgsEnc("   ", "   ", true, false, null);

        // then
        assertFalse(result);
        verify(shellConsole).printInfo("Required input, type command with --inf ${path} or --inc ${input}.");
    }

    @Test
    public void shouldReturnFalseWhenNoOutputProvided() {
        // given
        Validator validator = new Validator(shellConsole);

        // when
        boolean result = validator.isValidArgsEnc("/path/to/file", null, false, false, null);

        // then
        assertFalse(result);
        verify(shellConsole).printInfo("Required output, type command with args --outbuf or --outc or --outf ${path}.");
    }

    @Test
    public void shouldReturnFalseWhenNoOutputProvidedWithEmptyOutputFile() {
        // given
        Validator validator = new Validator(shellConsole);

        // when
        boolean result = validator.isValidArgsEnc("/path/to/file", null, false, false, "");

        // then
        assertFalse(result);
        verify(shellConsole).printInfo("Required output, type command with args --outbuf or --outc or --outf ${path}.");
    }

    @Test
    public void shouldReturnTrueWhenConsoleInputAndConsoleOutput() {
        // given
        Validator validator = new Validator(shellConsole);

        // when
        boolean result = validator.isValidArgsEnc(null, "test content", true, false, null);

        // then
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenBothInputsProvidedAndConsoleOutput() {
        // given
        Validator validator = new Validator(shellConsole);

        // when - both inf and inc provided (file takes precedence based on ReaderType logic)
        boolean result = validator.isValidArgsEnc("/path/to/file", "console input", true, false, null);

        // then
        assertTrue(result);
    }
}
