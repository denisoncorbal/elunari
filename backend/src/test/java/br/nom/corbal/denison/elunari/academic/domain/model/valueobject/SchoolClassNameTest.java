package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SchoolClassNameTest {
    @Test
    public void givenValidSchoolClassName_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            SchoolClassName schoolClassName = new SchoolClassName(
                    "Test");
            assertNotNull(schoolClassName);
        });
    }

    @Test
    public void givenInvalidSchoolClassName_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SchoolClassName(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SchoolClassName(
                            "");
                });
    }
}
