package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ResponseToInterventionStatusTest {
    @Test
    public void givenValidResponseToInterventionStatus_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            ResponseToInterventionStatus responseToInterventionStatus = new ResponseToInterventionStatus(
                    "ACTIVE");
            assertNotNull(responseToInterventionStatus);
        });

        assertDoesNotThrow(() -> {
            ResponseToInterventionStatus responseToInterventionStatus = new ResponseToInterventionStatus(
                    "INACTIVE");
            assertNotNull(responseToInterventionStatus);
        });
    }

    @Test
    public void givenInvalidResponseToInterventionStatus_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new ResponseToInterventionStatus(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new ResponseToInterventionStatus(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new ResponseToInterventionStatus(
                            "ANYTHING");
                });
    }
}
