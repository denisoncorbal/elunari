package br.nom.corbal.denison.elunari.academic.domain.repository;

import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.EnglishLanguageDevelopmentRecordAggregate;

public interface EnglishLanguageDevelopmentRecordRepository {
    public EnglishLanguageDevelopmentRecordAggregate save(
            EnglishLanguageDevelopmentRecordAggregate englishLanguageDevelopmentRecordAggregate);
}
