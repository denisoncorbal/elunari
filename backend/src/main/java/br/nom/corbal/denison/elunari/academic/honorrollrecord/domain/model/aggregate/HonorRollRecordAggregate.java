package br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.model.aggregate;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.model.valueobject.HonorRollLevel;

public class HonorRollRecordAggregate {
    private final UUID id;

    public UUID getId() {
        return id;
    }

    private UUID teacherId;
    private UUID studentId;
    private UUID schoolClassId;

    private HonorRollLevel level;

    public HonorRollRecordAggregate(UUID teacherId, UUID studentId, UUID schoolClassId, HonorRollLevel level) {
        this.id = UUID.randomUUID();
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.schoolClassId = schoolClassId;
        this.level = level;
    }
}
