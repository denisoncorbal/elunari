package br.nom.corbal.denison.elunari.academic.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SubjectStatus;

public class SubjectStatusTest {
    @Test
    public void givenValidSubjectStatus_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            SubjectStatus subjectStatus = new SubjectStatus(
                    "ACTIVE");
            assertNotNull(subjectStatus);
        });

        assertDoesNotThrow(() -> {
            SubjectStatus subjectStatus = new SubjectStatus(
                    "INACTIVE");
            assertNotNull(subjectStatus);
        });
    }

    @Test
    public void givenInvalidStudentStatus_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SubjectStatus(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SubjectStatus(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SubjectStatus(
                            "ANYTHING");
                });
    }
}
