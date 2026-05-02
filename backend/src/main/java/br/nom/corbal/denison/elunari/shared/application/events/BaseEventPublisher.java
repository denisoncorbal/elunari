package br.nom.corbal.denison.elunari.shared.application.events;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public interface BaseEventPublisher<T extends BaseEvent> {
    public void publish(T event);
}
