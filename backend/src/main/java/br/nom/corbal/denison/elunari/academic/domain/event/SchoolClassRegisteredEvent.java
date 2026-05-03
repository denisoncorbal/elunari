package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

public class SchoolClassRegisteredEvent extends BaseSchoolClassEvent {
    UUID schoolClassId;

    public SchoolClassRegisteredEvent(UUID schoolClassId) {
        super();
        this.schoolClassId = schoolClassId;
    }

    public SchoolClassRegisteredEvent(UUID correlationId, UUID schoolClassId) {
        super(correlationId);
        this.schoolClassId = schoolClassId;
    }
}
