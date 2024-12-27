package cc.realtec.real.auth.server.utils.formatter;

import org.jetbrains.annotations.NotNull;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {
    private final DateTimeFormatter formatter;

    public LocalDateFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @NotNull
    @Override
    public LocalDate parse(@NotNull String text, @NotNull Locale locale) {
        return LocalDate.parse(text, formatter);
    }

    @NotNull
    @Override
    public String print(LocalDate object, @NotNull Locale locale) {
        return object.format(formatter);
    }
}
