package br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.event;

import java.util.UUID;

public class ReadingLevelRecordEnteredEvent extends BaseReadingLevelRecordEvent {
    UUID readingLevelRecordId;

    public ReadingLevelRecordEnteredEvent(UUID readingLevelRecordId) {
        super();
        this.readingLevelRecordId = readingLevelRecordId;
    }

    public ReadingLevelRecordEnteredEvent(UUID correlationId, UUID readingLevelRecordId) {
        super(correlationId);
        this.readingLevelRecordId = readingLevelRecordId;
    }
}
