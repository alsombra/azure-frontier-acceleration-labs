package com.contoso.rh.util;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class DateUtilTest {

    private final DateUtil util = new DateUtil();

    @Test
    public void formatIso_formatoCorreto() {
        Calendar c = new GregorianCalendar(2024, Calendar.MARCH, 15);
        assertEquals("2024-03-15", util.formatIso(c.getTime()));
    }

    @Test
    public void formatBr_formatoCorreto() {
        Calendar c = new GregorianCalendar(2024, Calendar.MARCH, 15);
        assertEquals("15/03/2024", util.formatBr(c.getTime()));
    }

    @Test
    public void parseIso_recuperaData() throws ParseException {
        Date d = util.parseIso("2024-03-15");
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        assertEquals(2024, c.get(Calendar.YEAR));
        assertEquals(Calendar.MARCH, c.get(Calendar.MONTH));
        assertEquals(15, c.get(Calendar.DAY_OF_MONTH));
    }
}
