package br.nom.corbal.denison.elunari.registration.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.TeacherStatus;

public class TeacherStatusTest {
    @Test
    public void givenValidTeacherStatus_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            TeacherStatus teacherStatus = new TeacherStatus(
                    "ACTIVE");
            assertNotNull(teacherStatus);
        });

        assertDoesNotThrow(() -> {
            TeacherStatus teacherStatus = new TeacherStatus(
                    "INACTIVE");
            assertNotNull(teacherStatus);
        });
    }

    @Test
    public void givenInvalidTeacherStatus_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TeacherStatus(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TeacherStatus(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TeacherStatus(
                            "ANYTHING");
                });
    }
}
