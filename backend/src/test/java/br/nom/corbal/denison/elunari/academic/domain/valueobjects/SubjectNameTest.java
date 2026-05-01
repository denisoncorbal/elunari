package br.nom.corbal.denison.elunari.academic.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectNameTest {
    @Test
    public void givenValidSubjectName_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            SubjectName subjectName = new SubjectName(
                    "Test");
            assertNotNull(subjectName);
        });
    }

    @Test
    public void givenInvalidEmail_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SubjectName(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SubjectName(
                            "");
                });
    }
}
