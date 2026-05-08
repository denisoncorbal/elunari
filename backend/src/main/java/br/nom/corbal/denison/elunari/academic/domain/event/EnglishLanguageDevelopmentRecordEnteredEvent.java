package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

public class EnglishLanguageDevelopmentRecordEnteredEvent extends BaseEnglishLanguageDevelopmentRecordEvent {
    UUID englishLanguageDevelopmentRecordId;

    public EnglishLanguageDevelopmentRecordEnteredEvent(UUID englishLanguageDevelopmentRecordId) {
        super();
        this.englishLanguageDevelopmentRecordId = englishLanguageDevelopmentRecordId;
    }

    public EnglishLanguageDevelopmentRecordEnteredEvent(UUID correlationId, UUID englishLanguageDevelopmentRecordId) {
        super(correlationId);
        this.englishLanguageDevelopmentRecordId = englishLanguageDevelopmentRecordId;
    }
}
