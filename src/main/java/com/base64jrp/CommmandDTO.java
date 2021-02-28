package com.base64jrp;


import com.base64jrp.reader.ReaderType;
import com.base64jrp.writer.WriterType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author maximboguns
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommmandDTO {

    private String inputContent;
    private String outputFile;
    private ReaderType readerType;
    private WriterType writerType;

    public static CommmandDTO from(String inf, boolean outc, String inc, boolean outbuf, String outf) {
        Pair<ReaderType, String> pair = ReaderType.valueOf(inf, inc);
        return new CommmandDTO(pair.getValue(), outf, pair.getKey(), WriterType.valueOf(outc, outbuf, outf));
    }
}
