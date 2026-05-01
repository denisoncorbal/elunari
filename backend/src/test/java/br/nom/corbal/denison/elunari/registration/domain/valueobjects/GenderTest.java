package br.nom.corbal.denison.elunari.registration.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {
    @Test
    public void givenValidGender_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            Gender gender = new Gender(
                    "M");
            assertNotNull(gender);
        });

        assertDoesNotThrow(() -> {
            Gender gender = new Gender(
                    "F");
            assertNotNull(gender);
        });
    }

    @Test
    public void givenInvalidGender_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Gender(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Gender(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Gender(
                            "ANYTHING");
                });
    }
}
