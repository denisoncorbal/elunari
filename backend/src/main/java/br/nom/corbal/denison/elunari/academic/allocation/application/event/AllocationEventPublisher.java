package br.nom.corbal.denison.elunari.academic.allocation.application.event;

import br.nom.corbal.denison.elunari.academic.allocation.domain.event.BaseAllocationEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface AllocationEventPublisher<T extends BaseAllocationEvent> extends BaseEventPublisher<T> {

}
