package com.contoso.rh.util;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Encoder/Decoder usando classes proprietárias sun.misc (removidas no Java 9+).
 * Será alvo da modernização para java.util.Base64.
 */
@Component
public class PasswordEncoder {

    public String encode(String plain) {
        if (plain == null) return null;
        BASE64Encoder enc = new BASE64Encoder();
        return enc.encode(plain.getBytes(StandardCharsets.UTF_8));
    }

    public String decode(String encoded) {
        if (encoded == null) return null;
        try {
            BASE64Decoder dec = new BASE64Decoder();
            return new String(dec.decodeBuffer(encoded), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
