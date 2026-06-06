package br.nom.corbal.denison.elunari.academic.allocation.domain.model.aggregate;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.allocation.domain.model.valueobject.TimePeriod;

public class AllocationAggregate {
    UUID id;

    public UUID getId() {
        return id;
    }

    UUID teacherId;
    UUID subjectId;
    UUID schoolClassId;
    TimePeriod period;

    public TimePeriod getPeriod() {
        return period;
    }

    public AllocationAggregate(UUID teacherId, UUID subjectId, UUID schoolClassId, TimePeriod period) {
        this.id = UUID.randomUUID();
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.schoolClassId = schoolClassId;
        this.period = period;
    }

}
