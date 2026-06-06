package br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeTest {
    @Test
    public void givenValidGrade_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            Grade grade = new Grade(
                    "KINDERGARTEN");
            assertNotNull(grade);
        });
    }

    @Test
    public void givenInvalidGrade_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Grade(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Grade(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Grade(
                            "ANYTHING");
                });
    }
}
