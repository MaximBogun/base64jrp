package com.base64jrp;

import com.base64jrp.reader.ReaderType;
import com.base64jrp.writer.WriterType;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommmandDTOTest {

    @Test
    public void shouldCreateDTOWithFileReaderAndConsoleWriter() {
        // given
        String inf = "/path/to/input.txt";
        boolean outc = true;
        String inc = null;
        boolean outbuf = false;
        String outf = null;

        // when
        CommmandDTO dto = CommmandDTO.from(inf, outc, inc, outbuf, outf);

        // then
        assertEquals(inf, dto.getInputContent());
        assertNull(dto.getOutputFile());
        assertEquals(ReaderType.FILE, dto.getReaderType());
        assertEquals(WriterType.CONSOLE, dto.getWriterType());
    }

    @Test
    public void shouldCreateDTOWithConsoleReaderAndBufferWriter() {
        // given
        String inf = null;
        boolean outc = false;
        String inc = "console input content";
        boolean outbuf = true;
        String outf = null;

        // when
        CommmandDTO dto = CommmandDTO.from(inf, outc, inc, outbuf, outf);

        // then
        assertEquals(inc, dto.getInputContent());
        assertNull(dto.getOutputFile());
        assertEquals(ReaderType.CONSOLE, dto.getReaderType());
        assertEquals(WriterType.BUFFER, dto.getWriterType());
    }

    @Test
    public void shouldCreateDTOWithFileReaderAndFileWriter() {
        // given
        String inf = "/path/to/input.txt";
        boolean outc = false;
        String inc = null;
        boolean outbuf = false;
        String outf = "/path/to/output.txt";

        // when
        CommmandDTO dto = CommmandDTO.from(inf, outc, inc, outbuf, outf);

        // then
        assertEquals(inf, dto.getInputContent());
        assertEquals(outf, dto.getOutputFile());
        assertEquals(ReaderType.FILE, dto.getReaderType());
        assertEquals(WriterType.FILE, dto.getWriterType());
    }

    @Test
    public void shouldPrioritizeFileInputOverConsoleInput() {
        // given - both inputs provided
        String inf = "/path/to/input.txt";
        boolean outc = true;
        String inc = "console input";
        boolean outbuf = false;
        String outf = null;

        // when
        CommmandDTO dto = CommmandDTO.from(inf, outc, inc, outbuf, outf);

        // then - file should take priority
        assertEquals(inf, dto.getInputContent());
        assertEquals(ReaderType.FILE, dto.getReaderType());
    }

    @Test
    public void shouldPrioritizeConsoleOutputOverBufferOutput() {
        // given - both outc and outbuf are true
        String inf = "/path/to/input.txt";
        boolean outc = true;
        String inc = null;
        boolean outbuf = true;
        String outf = null;

        // when
        CommmandDTO dto = CommmandDTO.from(inf, outc, inc, outbuf, outf);

        // then - console should take priority
        assertEquals(WriterType.CONSOLE, dto.getWriterType());
    }

    @Test
    public void shouldUseDefaultConstructor() {
        // given/when
        CommmandDTO dto = new CommmandDTO();

        // then
        assertNull(dto.getInputContent());
        assertNull(dto.getOutputFile());
        assertNull(dto.getReaderType());
        assertNull(dto.getWriterType());
    }

    @Test
    public void shouldUseAllArgsConstructor() {
        // given
        String inputContent = "test content";
        String outputFile = "/path/to/output";
        ReaderType readerType = ReaderType.CONSOLE;
        WriterType writerType = WriterType.FILE;

        // when
        CommmandDTO dto = new CommmandDTO(inputContent, outputFile, readerType, writerType);

        // then
        assertEquals(inputContent, dto.getInputContent());
        assertEquals(outputFile, dto.getOutputFile());
        assertEquals(readerType, dto.getReaderType());
        assertEquals(writerType, dto.getWriterType());
    }

    @Test
    public void shouldUseSetters() {
        // given
        CommmandDTO dto = new CommmandDTO();

        // when
        dto.setInputContent("new input");
        dto.setOutputFile("new output");
        dto.setReaderType(ReaderType.FILE);
        dto.setWriterType(WriterType.BUFFER);

        // then
        assertEquals("new input", dto.getInputContent());
        assertEquals("new output", dto.getOutputFile());
        assertEquals(ReaderType.FILE, dto.getReaderType());
        assertEquals(WriterType.BUFFER, dto.getWriterType());
    }
}
