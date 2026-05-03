package br.nom.corbal.denison.elunari.registration.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NationalityTest {
    @Test
    public void givenValidNationality_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            Nationality nationality = new Nationality(
                    "Brazilian");
            assertNotNull(nationality);
        });
    }

    @Test
    public void givenInvalidNationality_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Nationality(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Nationality(
                            "");
                });
    }
}
