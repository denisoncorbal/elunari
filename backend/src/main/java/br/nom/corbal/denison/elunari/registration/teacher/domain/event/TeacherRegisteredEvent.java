package br.nom.corbal.denison.elunari.registration.teacher.domain.event;

import java.util.UUID;

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
