package br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.event;

import java.util.UUID;

public class HonorRollRecordEnteredEvent extends BaseHonorRollRecordEvent {
    UUID honorRollRecordId;

    public HonorRollRecordEnteredEvent(UUID honorRollRecordId) {
        super();
        this.honorRollRecordId = honorRollRecordId;
    }

    public HonorRollRecordEnteredEvent(UUID correlationId, UUID honorRollRecordId) {
        super(correlationId);
        this.honorRollRecordId = honorRollRecordId;
    }
}
