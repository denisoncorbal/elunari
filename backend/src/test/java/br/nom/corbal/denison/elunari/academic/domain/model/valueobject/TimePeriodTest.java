package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class TimePeriodTest {
    @Test
    public void givenValidTimePeriod_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            TimePeriod timePeriod = new TimePeriod(
                    LocalTime.now(),
                    LocalTime.now().plusMinutes(1));
            assertNotNull(timePeriod);
        });
    }

    @Test
    public void givenInvalidTimePeriod_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TimePeriod(
                            null, LocalTime.now());
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TimePeriod(
                            LocalTime.now(), null);
                });

        final LocalTime start = LocalTime.now();
        final LocalTime end = start;

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TimePeriod(
                            start, end);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TimePeriod(
                            LocalTime.now(), LocalTime.now().minusMinutes(1));
                });
    }
}
