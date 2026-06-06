package br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.repository;

import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.model.aggregate.EnglishLanguageDevelopmentRecordAggregate;

public interface EnglishLanguageDevelopmentRecordRepository {
    public EnglishLanguageDevelopmentRecordAggregate save(
            EnglishLanguageDevelopmentRecordAggregate englishLanguageDevelopmentRecordAggregate);
}
