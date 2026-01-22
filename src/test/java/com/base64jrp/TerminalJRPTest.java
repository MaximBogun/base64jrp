package com.base64jrp;

import com.base64jrp.decoder.Decoder;
import com.base64jrp.decoder.FactoryDecoder;
import com.base64jrp.encoder.Encoder;
import com.base64jrp.encoder.FactoryEncoder;
import com.base64jrp.reader.FactoryReader;
import com.base64jrp.reader.Reader;
import com.base64jrp.reader.ReaderType;
import com.base64jrp.validator.Validator;
import com.base64jrp.writer.FactoryWriter;
import com.base64jrp.writer.Writer;
import com.base64jrp.writer.WriterType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TerminalJRPTest {

    @Mock
    private FactoryEncoder factoryEncoder;

    @Mock
    private FactoryDecoder factoryDecoder;

    @Mock
    private FactoryWriter factoryWriter;

    @Mock
    private FactoryReader factoryReader;

    @Mock
    private Validator validator;

    @Mock
    private Encoder encoder;

    @Mock
    private Decoder decoder;

    @Mock
    private Reader reader;

    @Mock
    private Writer writer;

    @InjectMocks
    private TerminalJRP terminalJRP;

    @Test
    public void shouldEncodeWhenValidationPasses() {
        // given
        String inf = "/path/to/input.txt";
        String inc = null;
        boolean outc = true;
        boolean outbuf = false;
        String outf = null;

        when(validator.isValidArgsEnc(inf, inc, outc, outbuf, outf)).thenReturn(true);
        when(factoryReader.getReader(ReaderType.FILE)).thenReturn(reader);
        when(factoryWriter.getWriter(eq(WriterType.CONSOLE), isNull())).thenReturn(writer);
        when(factoryEncoder.getEncoder(eq(CodecType.BASE64), eq(writer), eq(reader))).thenReturn(encoder);

        // when
        terminalJRP.enc(inf, inc, outc, outbuf, outf);

        // then
        verify(encoder).encode(inf);
    }

    @Test
    public void shouldNotEncodeWhenValidationFails() {
        // given
        String inf = null;
        String inc = null;
        boolean outc = true;
        boolean outbuf = false;
        String outf = null;

        when(validator.isValidArgsEnc(inf, inc, outc, outbuf, outf)).thenReturn(false);

        // when
        terminalJRP.enc(inf, inc, outc, outbuf, outf);

        // then
        verify(factoryEncoder, never()).getEncoder(any(), any(), any());
    }

    @Test
    public void shouldDecodeWhenValidationPasses() {
        // given
        String inf = "/path/to/input.txt";
        String inc = null;
        boolean outc = true;
        boolean outbuf = false;
        String outf = null;

        when(validator.isValidArgsEnc(inf, inc, outc, outbuf, outf)).thenReturn(true);
        when(factoryReader.getReader(ReaderType.FILE)).thenReturn(reader);
        when(factoryWriter.getWriter(eq(WriterType.CONSOLE), isNull())).thenReturn(writer);
        when(factoryDecoder.getDecoder(eq(CodecType.BASE64), eq(writer), eq(reader))).thenReturn(decoder);

        // when
        terminalJRP.dec(inf, inc, outc, outbuf, outf);

        // then
        verify(decoder).decode(inf);
    }

    @Test
    public void shouldNotDecodeWhenValidationFails() {
        // given
        String inf = null;
        String inc = null;
        boolean outc = true;
        boolean outbuf = false;
        String outf = null;

        when(validator.isValidArgsEnc(inf, inc, outc, outbuf, outf)).thenReturn(false);

        // when
        terminalJRP.dec(inf, inc, outc, outbuf, outf);

        // then
        verify(factoryDecoder, never()).getDecoder(any(), any(), any());
    }

    @Test
    public void shouldEncodeWithConsoleInputAndFileOutput() {
        // given
        String inf = null;
        String inc = "test content";
        boolean outc = false;
        boolean outbuf = false;
        String outf = "/path/to/output.txt";

        when(validator.isValidArgsEnc(inf, inc, outc, outbuf, outf)).thenReturn(true);
        when(factoryReader.getReader(ReaderType.CONSOLE)).thenReturn(reader);
        when(factoryWriter.getWriter(eq(WriterType.FILE), eq(outf))).thenReturn(writer);
        when(factoryEncoder.getEncoder(eq(CodecType.BASE64), eq(writer), eq(reader))).thenReturn(encoder);

        // when
        terminalJRP.enc(inf, inc, outc, outbuf, outf);

        // then
        verify(encoder).encode(inc);
    }

    @Test
    public void shouldDecodeWithConsoleInputAndBufferOutput() {
        // given
        String inf = null;
        String inc = "SGVsbG8gV29ybGQ="; // "Hello World" in Base64
        boolean outc = false;
        boolean outbuf = true;
        String outf = null;

        when(validator.isValidArgsEnc(inf, inc, outc, outbuf, outf)).thenReturn(true);
        when(factoryReader.getReader(ReaderType.CONSOLE)).thenReturn(reader);
        when(factoryWriter.getWriter(eq(WriterType.BUFFER), isNull())).thenReturn(writer);
        when(factoryDecoder.getDecoder(eq(CodecType.BASE64), eq(writer), eq(reader))).thenReturn(decoder);

        // when
        terminalJRP.dec(inf, inc, outc, outbuf, outf);

        // then
        verify(decoder).decode(inc);
    }
}
