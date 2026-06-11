package br.nom.corbal.denison.elunari.academic.assignement.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AssignementStatusTest {
    @Test
    public void givenValidAssignementStatus_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            AssignementStatus assignementStatus = new AssignementStatus(
                    "ACTIVE");
            assertNotNull(assignementStatus);
        });

        assertDoesNotThrow(() -> {
            AssignementStatus assignementStatus = new AssignementStatus(
                    "GRADED");
            assertNotNull(assignementStatus);
        });

        assertDoesNotThrow(() -> {
            AssignementStatus assignementStatus = new AssignementStatus(
                    "INACTIVE");
            assertNotNull(assignementStatus);
        });
    }

    @Test
    public void givenInvalidAssignementStatus_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new AssignementStatus(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new AssignementStatus(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new AssignementStatus(
                            "ANYTHING");
                });
    }
}
