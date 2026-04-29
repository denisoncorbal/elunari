package br.nom.corbal.denison.elunari.shared.application.events;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEvent {
    UUID id;
    UUID correlationId;
    LocalDateTime createdAt;

    public BaseEvent() {
        this.id = UUID.randomUUID();
        this.correlationId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public BaseEvent(UUID correlationId) {
        this.id = UUID.randomUUID();
        this.correlationId = correlationId;
        this.createdAt = LocalDateTime.now();
    }

}
