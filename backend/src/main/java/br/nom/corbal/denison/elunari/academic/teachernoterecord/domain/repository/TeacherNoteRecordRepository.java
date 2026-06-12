package br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.repository;

import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.aggregate.TeacherNoteRecordAggregate;

public interface TeacherNoteRecordRepository {
    public TeacherNoteRecordAggregate save(TeacherNoteRecordAggregate teacherNoteRecordAggregate);
}
