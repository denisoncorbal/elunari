package br.nom.corbal.denison.elunari.registration.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentStatusTest {
    @Test
    public void givenValidStudentStatus_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            StudentStatus studentStatus = new StudentStatus(
                    "ACTIVE");
            assertNotNull(studentStatus);
        });

        assertDoesNotThrow(() -> {
            StudentStatus studentStatus = new StudentStatus(
                    "INACTIVE");
            assertNotNull(studentStatus);
        });
    }

    @Test
    public void givenInvalidStudentStatus_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new StudentStatus(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new StudentStatus(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new StudentStatus(
                            "ANYTHING");
                });
    }
}
