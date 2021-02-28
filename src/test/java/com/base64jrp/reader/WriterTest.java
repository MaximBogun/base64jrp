package com.base64jrp.reader;

import static com.base64jrp.reader.ReaderType.*;
import static com.base64jrp.writer.WriterType.FILE;
import static org.junit.Assert.assertEquals;

import com.base64jrp.reader.runner.CliAppRunner;
import com.base64jrp.shell.ShellConsole;
import com.base64jrp.writer.FactoryWriter;
import com.base64jrp.writer.Writer;
import com.base64jrp.writer.WriterType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CliAppRunner.class, FactoryWriter.class, FactoryReader.class})
@AutoConfigureMockMvc
public class WriterTest {

    @MockBean
    ShellConsole shellConsole;
    @Autowired
    FactoryWriter factoryWriter;
    @Autowired
    FactoryReader factoryReader;

    @Test
    public void shouldWriteFileToFileSystem() {
        //given
        factoryWriter.getWriter(FILE, getClass().getResource("/").getPath() + "FileToWrite.txt").write("test".getBytes());
        //when
        String content = factoryReader.getReader(ReaderType.FILE).read(getClass().getResource("/FileToWrite.txt").getPath());
        //then
        assertEquals(content, "test");
    }


}
