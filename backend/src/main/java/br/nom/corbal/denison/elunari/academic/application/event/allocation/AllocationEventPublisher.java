package br.nom.corbal.denison.elunari.academic.application.event.allocation;

import br.nom.corbal.denison.elunari.academic.domain.event.BaseAllocationEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface AllocationEventPublisher<T extends BaseAllocationEvent> extends BaseEventPublisher<T> {

}
