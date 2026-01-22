package com.base64jrp.decoder;

import com.base64jrp.CodecType;
import com.base64jrp.reader.Reader;
import com.base64jrp.writer.Writer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FactoryDecoderTest {

    @Mock
    private Reader reader;

    @Mock
    private Writer writer;

    @Test
    public void shouldReturnBase64DecoderWhenCodecTypeIsBase64() {
        // given
        FactoryDecoder factoryDecoder = new FactoryDecoder();

        // when
        Decoder decoder = factoryDecoder.getDecoder(CodecType.BASE64, writer, reader);

        // then
        assertNotNull(decoder);
        assertTrue(decoder instanceof Base64Decoder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCodecTypeIsNull() {
        // given
        FactoryDecoder factoryDecoder = new FactoryDecoder();

        // when
        factoryDecoder.getDecoder(null, writer, reader);

        // then - exception is expected
    }
}
