package com.base64jrp.writer;

import com.base64jrp.shell.ShellConsole;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author maximboguns
 */
@AllArgsConstructor
public class FileWriter implements Writer {

    private final String outbuf;
    private final ShellConsole shellConsole;

    @SneakyThrows
    @Override
    public void write(byte[] input) {
        Files.write(Paths.get(outbuf), input);
        shellConsole.printSuccess(String.format("The input saved by path %s", outbuf));
    }
}
