package com.base64jrp.decoder;

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
public class Base64DecoderTest {

    @Mock
    private Reader reader;

    @Mock
    private Writer writer;

    @Test
    public void shouldDecodeBase64ToString() {
        // given
        String input = "testInput";
        String originalContent = "Hello World";
        String encodedContent = Base64.getEncoder().encodeToString(originalContent.getBytes());
        when(reader.read(input)).thenReturn(encodedContent);

        Base64Decoder decoder = new Base64Decoder(reader, writer);

        // when
        decoder.decode(input);

        // then
        ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);
        verify(writer).write(captor.capture());

        String actualDecoded = new String(captor.getValue());
        assertEquals(originalContent, actualDecoded);
    }

    @Test
    public void shouldDecodeEmptyBase64String() {
        // given
        String input = "testInput";
        String originalContent = "";
        String encodedContent = Base64.getEncoder().encodeToString(originalContent.getBytes());
        when(reader.read(input)).thenReturn(encodedContent);

        Base64Decoder decoder = new Base64Decoder(reader, writer);

        // when
        decoder.decode(input);

        // then
        ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);
        verify(writer).write(captor.capture());

        String actualDecoded = new String(captor.getValue());
        assertEquals(originalContent, actualDecoded);
    }

    @Test
    public void shouldDecodeSpecialCharacters() {
        // given
        String input = "testInput";
        String originalContent = "!@#$%^&*()_+-=[]{}|;':\",./<>?";
        String encodedContent = Base64.getEncoder().encodeToString(originalContent.getBytes());
        when(reader.read(input)).thenReturn(encodedContent);

        Base64Decoder decoder = new Base64Decoder(reader, writer);

        // when
        decoder.decode(input);

        // then
        ArgumentCaptor<byte[]> captor = ArgumentCaptor.forClass(byte[].class);
        verify(writer).write(captor.capture());

        String actualDecoded = new String(captor.getValue());
        assertEquals(originalContent, actualDecoded);
    }

    @Test
    public void shouldCallReaderWithCorrectInput() {
        // given
        String input = "myFilePath.txt";
        String encodedContent = Base64.getEncoder().encodeToString("content".getBytes());
        when(reader.read(input)).thenReturn(encodedContent);

        Base64Decoder decoder = new Base64Decoder(reader, writer);

        // when
        decoder.decode(input);

        // then
        verify(reader).read(input);
    }
}
