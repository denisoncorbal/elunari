package br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.valueobject.TeacherNoteData;

public class TeacherNoteDataTest {
    @Test
    public void givenValidTeacherNoteData_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            TeacherNoteData teacherNoteData = new TeacherNoteData(
                    "ANYTHING");
            assertNotNull(teacherNoteData);
        });
    }

    @Test
    public void givenInvalidTeacherNoteData_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TeacherNoteData(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TeacherNoteData(
                            "");
                });
    }
}
