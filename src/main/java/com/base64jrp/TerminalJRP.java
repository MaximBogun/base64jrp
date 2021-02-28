package com.base64jrp;

import static com.base64jrp.CodecType.BASE64;
import static com.base64jrp.CommmandDTO.from;
import static org.springframework.shell.standard.ShellOption.*;

import com.base64jrp.decoder.FactoryDecoder;
import com.base64jrp.encoder.FactoryEncoder;
import com.base64jrp.reader.FactoryReader;
import com.base64jrp.shell.ShellConsole;
import com.base64jrp.validator.Validator;
import com.base64jrp.writer.FactoryWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author maximboguns
 */
@ShellComponent
@Slf4j
@AllArgsConstructor
public class TerminalJRP {

    private final FactoryEncoder factoryEncoder;
    private final FactoryDecoder factoryDecoder;
    private final FactoryWriter factoryWriter;
    private final FactoryReader factoryReader;
    private final Validator validator;

    @ShellMethod("Encode content with using base64, for more info input help enc")
    public void enc(@ShellOption(defaultValue = NULL, help = "Input file") String inf, @ShellOption(defaultValue = NULL, help = "Input from terminal") String inc, @ShellOption(help = "Output to terminal ") boolean outc, @ShellOption(help = "Output to buffer") boolean outbuf, @ShellOption(defaultValue = NULL, help = "Output to file") String outf) {
        if (!validator.isValidArgsEnc(inf, inc, outc, outbuf, outf)) return;
        //THINK ABOUT AOP LOGGING?
        log.info("enc command called with params : inf {}, inc {}, outc {}, outbuf {}, outf {}", inf, inc, outc, outbuf, outf);
        CommmandDTO commmandDTO = from(inf, outc, inc, outbuf, outf);

        factoryEncoder.getEncoder(BASE64,
                factoryWriter.getWriter(commmandDTO.getWriterType(), commmandDTO.getOutputFile()),
                factoryReader.getReader(commmandDTO.getReaderType())
        ).encode(commmandDTO.getInputContent());
    }

    @ShellMethod("decode content with base64,  for more info input help dec")
    public void dec(@ShellOption(defaultValue = NULL, help = "Input file") String inf, @ShellOption(defaultValue = NULL, help = "Input from terminal") String inc, @ShellOption(help = "Output to terminal ") boolean outc, @ShellOption(help = "Output to buffer") boolean outbuf, @ShellOption(defaultValue = NULL, help = "Output to file") String outf) {
        if (!validator.isValidArgsEnc(inf, inc, outc, outbuf, outf)) return;
        //THINK ABOUT AOP LOGGING?
        log.info("dec command called with params : inf {}, inc {}, outc {}, outbuf {}, outf {}", inf, inc, outc, outbuf, outf);
        CommmandDTO commmandDTO = from(inf, outc, inc, outbuf, outf);

        factoryDecoder.getDecoder(BASE64,
                factoryWriter.getWriter(commmandDTO.getWriterType(), commmandDTO.getOutputFile()),
                factoryReader.getReader(commmandDTO.getReaderType())
        ).decode(commmandDTO.getInputContent());
    }


}