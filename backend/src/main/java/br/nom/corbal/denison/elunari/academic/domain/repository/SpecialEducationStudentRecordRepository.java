package br.nom.corbal.denison.elunari.academic.domain.repository;

import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.SpecialEducationStudentRecordAggregate;

public interface SpecialEducationStudentRecordRepository {
    public SpecialEducationStudentRecordAggregate save(
            SpecialEducationStudentRecordAggregate specialEducationStudentRecordAggregate);
}
