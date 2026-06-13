package br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.repository;

import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.model.aggregate.ReadingLevelRecordAggregate;

public interface ReadingLevelRecordRepository {
    public ReadingLevelRecordAggregate save(ReadingLevelRecordAggregate readingLevelRecordAggregate);
}
