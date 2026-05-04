package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

public class TeacherAllocatedEvent extends BaseAllocationEvent {
    UUID allocationId;

    public TeacherAllocatedEvent(UUID allocationId) {
        super();
        this.allocationId = allocationId;
    }

    public TeacherAllocatedEvent(UUID correlationId, UUID allocationId) {
        super(correlationId);
        this.allocationId = allocationId;
    }
}
