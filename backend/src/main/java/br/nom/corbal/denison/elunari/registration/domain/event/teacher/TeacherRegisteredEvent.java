package br.nom.corbal.denison.elunari.registration.domain.event.teacher;

import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.domain.event.BaseTeacherEvent;

public class TeacherRegisteredEvent extends BaseTeacherEvent {
    UUID teacherId;

    public TeacherRegisteredEvent(UUID teacherId) {
        super();
        this.teacherId = teacherId;
    }

    public TeacherRegisteredEvent(UUID correlationId, UUID teacherId) {
        super(correlationId);
        this.teacherId = teacherId;
    }
}
