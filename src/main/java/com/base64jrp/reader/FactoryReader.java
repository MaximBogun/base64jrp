package com.base64jrp.reader;

import org.springframework.stereotype.Component;

/**
 * @author maximboguns
 */
@Component
public class FactoryReader {
    public Reader getReader(ReaderType readerType) {
        switch (readerType) {
            case FILE:
                return new FileReader();
            case CONSOLE:
                return new ConsoleReader();
            default:
                throw new IllegalArgumentException("No such writer");
        }
    }
}
