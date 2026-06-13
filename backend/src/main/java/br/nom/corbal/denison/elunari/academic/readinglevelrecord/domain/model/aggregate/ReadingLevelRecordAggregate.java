package br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.model.aggregate;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.model.valueobject.ReadingLevelData;

public class ReadingLevelRecordAggregate {
    private final UUID id;

    public UUID getId() {
        return id;
    }

    private UUID teacherId;
    private UUID studentId;
    private UUID schoolClassId;

    private ReadingLevelData data;

    public ReadingLevelRecordAggregate(UUID id, UUID teacherId, UUID studentId, UUID schoolClassId,
            ReadingLevelData data) {
        this.id = UUID.randomUUID();
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.schoolClassId = schoolClassId;
        this.data = data;
    }
}
