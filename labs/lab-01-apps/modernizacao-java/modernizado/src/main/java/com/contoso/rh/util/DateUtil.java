package com.contoso.rh.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {

    // SimpleDateFormat NÃO é thread-safe — instância compartilhada (mau exemplo proposital)
    private static final SimpleDateFormat ISO = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat BR  = new SimpleDateFormat("dd/MM/yyyy");

    public String formatIso(Date date) {
        if (date == null) return null;
        return ISO.format(date);
    }

    public String formatBr(Date date) {
        if (date == null) return null;
        return BR.format(date);
    }

    public Date parseIso(String s) throws ParseException {
        if (s == null) return null;
        return ISO.parse(s);
    }
}
