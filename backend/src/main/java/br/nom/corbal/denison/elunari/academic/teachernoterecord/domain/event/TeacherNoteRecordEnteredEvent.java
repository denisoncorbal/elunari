package br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.event;

import java.util.UUID;

public class TeacherNoteRecordEnteredEvent extends BaseTeacherNoteRecordEvent {
    UUID teacherNoteRecordId;

    public TeacherNoteRecordEnteredEvent(UUID teacherNoteRecordId) {
        super();
        this.teacherNoteRecordId = teacherNoteRecordId;
    }

    public TeacherNoteRecordEnteredEvent(UUID correlationId, UUID teacherNoteRecordId) {
        super(correlationId);
        this.teacherNoteRecordId = teacherNoteRecordId;
    }
}
