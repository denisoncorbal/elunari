package br.nom.corbal.denison.elunari.registration.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {
    @Test
    public void givenValidEmail_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            Email email = new Email(
                    "test@test.com");
            assertNotNull(email);
        });
    }

    @Test
    public void givenInvalidEmail_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Email(
                            "test.test.com");
                });
    }
}
