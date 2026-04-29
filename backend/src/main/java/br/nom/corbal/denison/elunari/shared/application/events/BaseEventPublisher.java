package br.nom.corbal.denison.elunari.shared.application.events;

public interface BaseEventPublisher<T extends BaseEvent> {
    public void publish(T event);
}
