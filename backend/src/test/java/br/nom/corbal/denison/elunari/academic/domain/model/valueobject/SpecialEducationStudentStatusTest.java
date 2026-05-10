package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SpecialEducationStudentStatusTest {
    @Test
    public void givenValidSpecialEducationStudentStatus_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            SpecialEducationStudentStatus specialEducationStudentStatus = new SpecialEducationStudentStatus(
                    "ACTIVE");
            assertNotNull(specialEducationStudentStatus);
        });

        assertDoesNotThrow(() -> {
            SpecialEducationStudentStatus specialEducationStudentStatus = new SpecialEducationStudentStatus(
                    "INACTIVE");
            assertNotNull(specialEducationStudentStatus);
        });
    }

    @Test
    public void givenInvalidSpecialEducationStudentStatus_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SpecialEducationStudentStatus(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SpecialEducationStudentStatus(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SpecialEducationStudentStatus(
                            "ANYTHING");
                });
    }
}
