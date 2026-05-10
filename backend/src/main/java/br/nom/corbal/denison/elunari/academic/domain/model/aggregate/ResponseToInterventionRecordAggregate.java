package br.nom.corbal.denison.elunari.academic.domain.model.aggregate;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.ResponseToInterventionLevel;

public class ResponseToInterventionRecordAggregate {
    private final UUID id;

    public UUID getId() {
        return id;
    }

    private UUID teacherId;
    private UUID studentId;
    private UUID schoolClassId;
    private ResponseToInterventionLevel level;

    public ResponseToInterventionRecordAggregate(UUID teacherId, UUID studentId, UUID schoolClassId,
            ResponseToInterventionLevel level) {
        this.id = UUID.randomUUID();
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.schoolClassId = schoolClassId;
        this.level = level;
    }
}
