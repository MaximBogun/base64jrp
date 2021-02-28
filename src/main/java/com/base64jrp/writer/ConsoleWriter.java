package com.base64jrp.writer;

import com.base64jrp.shell.ShellConsole;
import java.io.PrintStream;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author maximboguns
 */
@AllArgsConstructor
public class ConsoleWriter implements Writer {

    private static final byte[] NEW_LINE = "\n".getBytes();
    private final ShellConsole shellConsole;

    @SneakyThrows
    @Override
    public void write(byte[] input) {
        PrintStream printStream = System.out;
        shellConsole.printSuccess("The input printed below");
        printStream.write(input);
        printStream.write(NEW_LINE);
        printStream.flush();
    }
}
