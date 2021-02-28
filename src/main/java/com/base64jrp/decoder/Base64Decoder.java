package com.base64jrp.decoder;

import com.base64jrp.reader.Reader;
import com.base64jrp.writer.Writer;
import java.util.Base64;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author maximboguns
 */
@Slf4j
@AllArgsConstructor
public class Base64Decoder implements Decoder {

    private final Reader reader;
    private final Writer writer;

    @Override
    public void decode(String input) {
        String content = reader.read(input);
        log.info("Decode input {}, content {}", input, content);
        var decoder = Base64.getDecoder();
        writer.write(decoder.decode(content));
    }
}
