package br.nom.corbal.denison.elunari.academic.domain.model.aggregate;

import java.time.LocalTime;
import java.util.UUID;

public class AllocationAggregate {
    UUID id;

    public UUID getId() {
        return id;
    }

    UUID teacherId;
    UUID subjectId;
    UUID schoolClassId;
    LocalTime startTime;
    LocalTime endTime;

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public AllocationAggregate(UUID teacherId, UUID subjectId, UUID schoolClassId, LocalTime startTime,
            LocalTime endTime) {
        this.id = UUID.randomUUID();
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.schoolClassId = schoolClassId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
