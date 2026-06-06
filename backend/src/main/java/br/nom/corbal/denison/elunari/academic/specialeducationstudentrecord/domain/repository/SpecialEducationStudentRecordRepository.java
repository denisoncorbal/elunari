package br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.domain.repository;

import br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.domain.model.aggregate.SpecialEducationStudentRecordAggregate;

public interface SpecialEducationStudentRecordRepository {
    public SpecialEducationStudentRecordAggregate save(
            SpecialEducationStudentRecordAggregate specialEducationStudentRecordAggregate);
}
