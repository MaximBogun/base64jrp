package com.base64jrp.encoder;

import com.base64jrp.CodecType;
import com.base64jrp.reader.Reader;
import com.base64jrp.writer.Writer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FactoryEncoderTest {

    @Mock
    private Reader reader;

    @Mock
    private Writer writer;

    @Test
    public void shouldReturnBase64EncoderWhenCodecTypeIsBase64() {
        // given
        FactoryEncoder factoryEncoder = new FactoryEncoder();

        // when
        Encoder encoder = factoryEncoder.getEncoder(CodecType.BASE64, writer, reader);

        // then
        assertNotNull(encoder);
        assertTrue(encoder instanceof Base64Encoder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCodecTypeIsNull() {
        // given
        FactoryEncoder factoryEncoder = new FactoryEncoder();

        // when
        factoryEncoder.getEncoder(null, writer, reader);

        // then - exception is expected
    }
}
