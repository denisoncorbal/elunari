package br.nom.corbal.denison.elunari.academic.domain.model.aggregate;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.EnrollmentStatus;

public class EnrollmentAggregate {
    private UUID id;

    public UUID getId() {
        return id;
    }

    private UUID studentId;
    private UUID schoolClassId;
    private EnrollmentStatus status;

    public EnrollmentAggregate(UUID studentId, UUID schoolClassId, EnrollmentStatus status) {
        this.id = UUID.randomUUID();
        this.studentId = studentId;
        this.schoolClassId = schoolClassId;
        this.status = status;
    }

}
