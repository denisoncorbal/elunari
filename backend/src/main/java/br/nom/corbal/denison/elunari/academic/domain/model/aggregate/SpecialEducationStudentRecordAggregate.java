package br.nom.corbal.denison.elunari.academic.domain.model.aggregate;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SpecialEducationStudentInformation;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SpecialEducationStudentStatus;

public class SpecialEducationStudentRecordAggregate {
    private final UUID id;

    public UUID getId() {
        return id;
    }

    private UUID teacherId;
    private UUID studentId;
    private UUID schoolClassId;
    private SpecialEducationStudentInformation information;
    private SpecialEducationStudentStatus status;

    public SpecialEducationStudentRecordAggregate(UUID teacherId, UUID studentId, UUID schoolClassId,
            SpecialEducationStudentInformation information, SpecialEducationStudentStatus status) {
        this.id = UUID.randomUUID();
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.schoolClassId = schoolClassId;
        this.information = information;
        this.status = status;
    }
}
