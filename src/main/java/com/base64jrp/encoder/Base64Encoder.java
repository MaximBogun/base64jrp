package com.base64jrp.encoder;

import static java.util.Base64.*;

import com.base64jrp.reader.Reader;
import com.base64jrp.writer.Writer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author maximboguns
 */
@Slf4j
@AllArgsConstructor
public class Base64Encoder implements Encoder {

    private final Reader reader;
    private final Writer writer;

    @Override
    public void encode(String input) {
        String content = reader.read(input);
        log.info("Encode input {}, content {}", input, content);
        var encoder = getEncoder();
        writer.write(encoder.encode(content.getBytes()));
    }

}
