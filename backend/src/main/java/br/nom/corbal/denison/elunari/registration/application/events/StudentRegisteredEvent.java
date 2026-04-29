package br.nom.corbal.denison.elunari.registration.application.events;

import java.util.UUID;

public class StudentRegisteredEvent extends BaseStudentEvent {
    UUID studentId;

    public StudentRegisteredEvent(UUID studentId) {
        super();
        this.studentId = studentId;
    }

    public StudentRegisteredEvent(UUID correlationId, UUID studentId) {
        super(correlationId);
        this.studentId = studentId;
    }
}
