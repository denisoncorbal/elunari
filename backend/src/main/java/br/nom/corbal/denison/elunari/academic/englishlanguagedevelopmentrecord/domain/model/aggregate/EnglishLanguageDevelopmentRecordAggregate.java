package br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.model.aggregate;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.model.valueobject.EnglishLanguageDevelopmentLevel;

public class EnglishLanguageDevelopmentRecordAggregate {
    private final UUID id;

    public UUID getId() {
        return id;
    }

    private UUID teacherId;
    private UUID studentId;
    private UUID schoolClassId;

    private EnglishLanguageDevelopmentLevel level;

    public EnglishLanguageDevelopmentRecordAggregate(UUID teacherId, UUID studentId, UUID schoolClassId,
            EnglishLanguageDevelopmentLevel level) {
        this.id = UUID.randomUUID();
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.schoolClassId = schoolClassId;
        this.level = level;
    }

}
