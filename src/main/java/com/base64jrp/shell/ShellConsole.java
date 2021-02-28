package com.base64jrp.shell;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@AllArgsConstructor
public class ShellConsole {

    private final Terminal terminal;

    public void print(String message) {
        print(message, null);
    }

    public void printSuccess(String message) {
        print(message, PromptColor.GREEN);
    }

    public void printInfo(String message) {
        print(message, PromptColor.YELLOW);
    }

    public void printWarning(String message) {
        print(message, PromptColor.YELLOW);
    }

    public void printError(String message) {
        print(message, PromptColor.RED);
    }

    public void print(String message, PromptColor color) {
        String toPrint = message;
        if (color != null) {
            toPrint = getColored(message, color);
        }
        terminal.writer().println(toPrint);
        terminal.flush();
    }

    private String getColored(String message, PromptColor color) {
        return (new AttributedStringBuilder()).append(message, AttributedStyle.DEFAULT.foreground(color.toJlineAttributedStyle())).toAnsi();
    }

    private enum PromptColor {
        RED(1),
        GREEN(2),
        YELLOW(3);

        private final int value;

        PromptColor(int value) {
            this.value = value;
        }

        public int toJlineAttributedStyle() {
            return this.value;
        }
    }
}
