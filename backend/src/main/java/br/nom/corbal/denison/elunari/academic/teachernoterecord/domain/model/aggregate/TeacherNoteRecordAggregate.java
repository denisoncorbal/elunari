package br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.aggregate;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.valueobject.TeacherNoteData;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.valueobject.TeacherNoteStatus;

public class TeacherNoteRecordAggregate {
    private final UUID id;

    public UUID getId() {
        return id;
    }

    private UUID teacherId;
    private UUID studentId;
    private UUID schoolClassId;
    private TeacherNoteData data;
    private TeacherNoteStatus status;

    public TeacherNoteRecordAggregate(UUID teacherId, UUID studentId, UUID schoolClassId, TeacherNoteData data,
            TeacherNoteStatus status) {
        this.id = UUID.randomUUID();
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.schoolClassId = schoolClassId;
        this.data = data;
        this.status = status;
    }
}
