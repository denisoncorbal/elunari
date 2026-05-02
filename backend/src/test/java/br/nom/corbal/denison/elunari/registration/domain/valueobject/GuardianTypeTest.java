package br.nom.corbal.denison.elunari.registration.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.GuardianType;

public class GuardianTypeTest {
    @Test
    public void givenValidGuardianType_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            GuardianType guardianType = new GuardianType(
                    "FATHER");
            assertNotNull(guardianType);
        });

        assertDoesNotThrow(() -> {
            GuardianType guardianType = new GuardianType(
                    "MOTHER");
            assertNotNull(guardianType);
        });

        assertDoesNotThrow(() -> {
            GuardianType guardianType = new GuardianType(
                    "OTHER");
            assertNotNull(guardianType);
        });
    }

    @Test
    public void givenInvalidGuardianType_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new GuardianType(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new GuardianType(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new GuardianType(
                            "ANYTHING");
                });
    }
}
