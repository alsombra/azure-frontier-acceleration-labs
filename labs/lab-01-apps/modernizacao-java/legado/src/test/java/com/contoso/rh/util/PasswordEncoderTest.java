package com.contoso.rh.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordEncoderTest {

    private final PasswordEncoder enc = new PasswordEncoder();

    @Test
    public void encodeDecode_roundTrip() {
        String original = "senha-secreta-123";
        String encoded = enc.encode(original);
        assertEquals(original, enc.decode(encoded));
    }
}
