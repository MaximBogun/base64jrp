package com.base64jrp.reader;

import com.base64jrp.exception.InputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author maximboguns
 */
@Slf4j
public class FileReader implements Reader {

    @SneakyThrows
    @Override
    public String read(String input) {

        Path path = Paths.get(input);

        if (!Files.exists(path)) {
            log.error("Can't read file {} ", path);
            throw new InputException("Input file doesn't exist");
        }

        String fileText = Files.readString(path);

        log.debug("File is read : fileText  {}", fileText);

        return fileText;
    }
}
