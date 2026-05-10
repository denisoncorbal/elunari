package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EnrollmentStatusTest {
    @Test
    public void givenValidEnrollmentStatus_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            EnrollmentStatus enrollmentStatus = new EnrollmentStatus(
                    "ACTIVE");
            assertNotNull(enrollmentStatus);
        });

        assertDoesNotThrow(() -> {
            EnrollmentStatus enrollmentStatus = new EnrollmentStatus(
                    "INACTIVE");
            assertNotNull(enrollmentStatus);
        });
    }

    @Test
    public void givenInvalidEnrollmentStatus_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new EnrollmentStatus(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new EnrollmentStatus(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new EnrollmentStatus(
                            "ANYTHING");
                });
    }
}
