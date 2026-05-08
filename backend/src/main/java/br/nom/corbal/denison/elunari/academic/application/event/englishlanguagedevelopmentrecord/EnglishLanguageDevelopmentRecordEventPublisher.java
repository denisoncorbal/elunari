package br.nom.corbal.denison.elunari.academic.application.event.englishlanguagedevelopmentrecord;

import br.nom.corbal.denison.elunari.academic.domain.event.BaseEnglishLanguageDevelopmentRecordEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface EnglishLanguageDevelopmentRecordEventPublisher<T extends BaseEnglishLanguageDevelopmentRecordEvent>
        extends BaseEventPublisher<T> {

}
