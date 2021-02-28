package com.base64jrp.reader;

import static com.base64jrp.reader.ReaderType.*;
import static org.junit.Assert.assertEquals;

import com.base64jrp.reader.runner.CliAppRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CliAppRunner.class, FactoryReader.class})
@AutoConfigureMockMvc
public class ReaderTest {

    @Autowired
    FactoryReader factoryReader;

    @Test
    public void shouldReadFile() {
        //given
        Reader reader = factoryReader.getReader(FILE);
        String expectedContent = "TestContent";
        //when
        String content = reader.read(getClass().getResource("/TestFile").getPath());
        //then
        assertEquals(expectedContent, content);
    }

    @Test
    public void shouldReturnContent() {
        //given
        Reader reader = factoryReader.getReader(CONSOLE);
        String expectedContent = "TestContent";
        //when
        String content = reader.read(expectedContent);
        //then
        assertEquals(expectedContent, content);
    }
}
