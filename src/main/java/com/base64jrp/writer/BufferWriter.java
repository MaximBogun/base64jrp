package com.base64jrp.writer;

import com.base64jrp.shell.ShellConsole;
import java.awt.*;
import java.awt.datatransfer.*;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author maximboguns
 */
@AllArgsConstructor
public class BufferWriter implements Writer {

    private final ShellConsole shellConsole;


    @SneakyThrows
    @Override
    public void write(byte[] input) {
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(new String(input, StandardCharsets.UTF_8));
        c.setContents(stringSelection, stringSelection);
        shellConsole.printSuccess("The input saved in your clipboard");
    }

}
