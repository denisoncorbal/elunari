package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class SchoolClassReferenceYearTest {
    @Test
    public void givenValidSchoolClassReferenceYear_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            SchoolClassReferenceYear schoolClassReferenceYear = new SchoolClassReferenceYear(
                    LocalDateTime.now().getYear());
            assertNotNull(schoolClassReferenceYear);
        });
    }

    @Test
    public void givenInvalidSchoolClassReferenceYear_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SchoolClassReferenceYear(
                            LocalDateTime.MAX.getYear() + 1);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new SchoolClassReferenceYear(
                            LocalDateTime.MIN.getYear() - 1);
                });
    }
}
