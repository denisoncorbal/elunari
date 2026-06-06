package br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.event;

import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.event.BaseEnglishLanguageDevelopmentRecordEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface EnglishLanguageDevelopmentRecordEventPublisher<T extends BaseEnglishLanguageDevelopmentRecordEvent>
                extends BaseEventPublisher<T> {

}
