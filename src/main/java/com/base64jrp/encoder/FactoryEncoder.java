package com.base64jrp.encoder;


import com.base64jrp.CodecType;
import com.base64jrp.reader.Reader;
import com.base64jrp.writer.Writer;
import org.springframework.stereotype.Service;

/**
 * @author maximboguns
 */
@Service
public class FactoryEncoder {

    public Encoder getEncoder(CodecType codecType, Writer writer, Reader reader) {
        if (codecType == CodecType.BASE64) {
            return new Base64Encoder(reader, writer);
        }
        throw new IllegalArgumentException(String.format("No such encoder with codec %s", codecType));
    }
}
