package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

public class StudentEnrolledEvent extends BaseEnrollmentEvent {
    UUID enrollmentId;

    public StudentEnrolledEvent(UUID enrollmentId) {
        super();
        this.enrollmentId = enrollmentId;
    }

    public StudentEnrolledEvent(UUID correlationId, UUID enrollmentId) {
        super(correlationId);
        this.enrollmentId = enrollmentId;
    }
}
