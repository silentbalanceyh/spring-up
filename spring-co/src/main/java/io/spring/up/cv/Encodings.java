package io.spring.up.cv;

import java.nio.charset.Charset;

public interface Encodings {
    /** **/
    String UTF_8 = "UTF-8";
    /** **/
    String US_ASCII = "US-ASCII";
    /** **/
    String ISO_8859_1 = "ISO-8859-1";

    Charset CHARSET = Charset.forName(UTF_8);
}
