package com.base64jrp.decoder;


import com.base64jrp.CodecType;
import com.base64jrp.encoder.Base64Encoder;
import com.base64jrp.encoder.Encoder;
import com.base64jrp.reader.Reader;
import com.base64jrp.writer.Writer;
import org.springframework.stereotype.Service;

/**
 * @author maximboguns
 */
@Service
public class FactoryDecoder {
    public Decoder getDecoder(CodecType codecType, Writer writer, Reader reader) {
        if (codecType == CodecType.BASE64) {
            return new Base64Decoder(reader, writer);
        }
        throw new IllegalArgumentException(String.format("No such decoder with codec %s", codecType));
    }
}
