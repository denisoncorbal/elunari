package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SchoolClassStatusTest {
    @Test
    public void givenValidSchoolClassStatus_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            SchoolClassStatus schoolClassStatus = new SchoolClassStatus(
                    "ACTIVE");
            assertNotNull(schoolClassStatus);
        });

        assertDoesNotThrow(() -> {
            SchoolClassStatus schoolClassStatus = new SchoolClassStatus(
                    "INACTIVE");
            assertNotNull(schoolClassStatus);
        });
    }

    @Test
    public void givenInvalidSchoolClassStatus_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SchoolClassStatus(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SchoolClassStatus(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SchoolClassStatus(
                            "ANYTHING");
                });
    }
}
