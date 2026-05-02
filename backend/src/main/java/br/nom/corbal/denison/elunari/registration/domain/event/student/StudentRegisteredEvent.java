package br.nom.corbal.denison.elunari.registration.domain.event.student;

import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.domain.event.BaseStudentEvent;

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
