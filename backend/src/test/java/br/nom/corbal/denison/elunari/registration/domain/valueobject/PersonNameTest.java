package br.nom.corbal.denison.elunari.registration.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.PersonName;

public class PersonNameTest {
    @Test
    public void givenValidPersonName_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            PersonName personName = new PersonName(
                    "First", "Last");
            assertNotNull(personName);
        });
    }

    @Test
    public void givenInvalidPersonName_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new PersonName(
                            null, null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new PersonName(
                            "First", null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new PersonName(
                            null, "Last");
                });
    }
}
