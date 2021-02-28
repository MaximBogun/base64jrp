package com.base64jrp.validator;

import com.base64jrp.shell.ShellConsole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    private final ShellConsole shellConsole;

    public Validator(ShellConsole shellConsole) {
        this.shellConsole = shellConsole;
    }

    public boolean isValidArgsEnc(String inf, String inc, boolean outc, boolean outbuf, String outf) {
        if (StringUtils.isBlank(inf) && StringUtils.isBlank(inc)) {
            shellConsole.printInfo("Required input, type command with --inf ${path} or --inc ${input}.");
            return false;
        }

        if (!outbuf && !outc && StringUtils.isBlank(outf)) {
            shellConsole.printInfo("Required output, type command with args --outbuf or --outc or --outf ${path}.");
            return false;
        }

        return true;
    }
}
