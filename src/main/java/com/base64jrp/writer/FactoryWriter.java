package com.base64jrp.writer;

import com.base64jrp.shell.ShellConsole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author maximboguns
 */
@Component
@AllArgsConstructor
public class FactoryWriter {

    private final ShellConsole shellConsole;

    public Writer getWriter(WriterType writerType, String outputFile) {
        switch (writerType) {
            case FILE:
                return new FileWriter(outputFile, shellConsole);
            case BUFFER:
                return new BufferWriter(shellConsole);
            case CONSOLE:
                return new ConsoleWriter(shellConsole);
            default:
                throw new IllegalArgumentException(String.format("No such writer %s", writerType.name()));
        }
    }
}
