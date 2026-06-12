package br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.valueobject.TeacherNoteStatus;

public class TeacherNoteStatusTest {
    @Test
    public void givenValidTeacherNoteStatus_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            TeacherNoteStatus teacherNoteStatus = new TeacherNoteStatus(
                    "ACTIVE");
            assertNotNull(teacherNoteStatus);
        });

        assertDoesNotThrow(() -> {
            TeacherNoteStatus teacherNoteStatus = new TeacherNoteStatus(
                    "INACTIVE");
            assertNotNull(teacherNoteStatus);
        });
    }

    @Test
    public void givenInvalidTeacherNoteStatus_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TeacherNoteStatus(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TeacherNoteStatus(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TeacherNoteStatus(
                            "ANYTHING");
                });
    }
}
