package com.mdnch.webmdnch.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));

    public static String formatFechaManual(LocalDate fechaManual) {
        if (fechaManual == null) {
            return null;
        }
        return fechaManual.format(FORMATTER);
    }
}