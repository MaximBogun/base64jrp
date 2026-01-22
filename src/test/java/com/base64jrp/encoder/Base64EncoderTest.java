package com.base64jrp.encoder;

import com.base64jrp.reader.Reader;
import com.base64jrp.writer.Writer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Base64EncoderTest {

    @Mock
    private Reader reader;

    @Mock
    private Writer writer;

    @Test
    public void shouldEncodeStringToBase64() {
        // given
        String input = "testInput";
        String content = "Hello World";
        when(reader.read(input)).thenReturn(content);

        Base64Encoder encoder = new Base64Encoder(reader, writer);

        // when
        encoder.encode(input);

        // then
        ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);
        verify(writer).write(captor.capture());

        String expectedEncoded = Base64.getEncoder().encodeToString(content.getBytes());
        String actualEncoded = new String(captor.getValue());
        assertEquals(expectedEncoded, actualEncoded);
    }

    @Test
    public void shouldEncodeEmptyString() {
        // given
        String input = "testInput";
        String content = "";
        when(reader.read(input)).thenReturn(content);

        Base64Encoder encoder = new Base64Encoder(reader, writer);

        // when
        encoder.encode(input);

        // then
        ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);
        verify(writer).write(captor.capture());

        String expectedEncoded = Base64.getEncoder().encodeToString(content.getBytes());
        String actualEncoded = new String(captor.getValue());
        assertEquals(expectedEncoded, actualEncoded);
    }

    @Test
    public void shouldEncodeSpecialCharacters() {
        // given
        String input = "testInput";
        String content = "!@#$%^&*()_+-=[]{}|;':\",./<>?";
        when(reader.read(input)).thenReturn(content);

        Base64Encoder encoder = new Base64Encoder(reader, writer);

        // when
        encoder.encode(input);

        // then
        ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);
        verify(writer).write(captor.capture());

        String expectedEncoded = Base64.getEncoder().encodeToString(content.getBytes());
        String actualEncoded = new String(captor.getValue());
        assertEquals(expectedEncoded, actualEncoded);
    }

    @Test
    public void shouldCallReaderWithCorrectInput() {
        // given
        String input = "myFilePath.txt";
        when(reader.read(input)).thenReturn("content");

        Base64Encoder encoder = new Base64Encoder(reader, writer);

        // when
        encoder.encode(input);

        // then
        verify(reader).read(input);
    }
}
