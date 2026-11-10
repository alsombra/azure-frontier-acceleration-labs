package com.contoso.rh.util;

import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class PasswordEncoder {

    public String encode(String plain) {
        if (plain == null) return null;
        byte[] bytes = plain.getBytes(StandardCharsets.UTF_8);
        String separator = System.lineSeparator();
        String encoded = Base64.getMimeEncoder(76, separator.getBytes(StandardCharsets.US_ASCII)).encodeToString(bytes);
        return bytes.length > 0 && bytes.length % 57 == 0 ? encoded + separator : encoded;
    }

    public String decode(String encoded) {
        if (encoded == null) return null;
        try {
            return new String(Base64.getMimeDecoder().decode(encoded), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
