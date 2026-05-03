package br.nom.corbal.denison.elunari.academic.application.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.nom.corbal.denison.elunari.academic.application.command.RegisterSchoolClassCommand;
import br.nom.corbal.denison.elunari.academic.application.event.schoolclass.SchoolClassEventPublisher;
import br.nom.corbal.denison.elunari.academic.domain.event.SchoolClassRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.model.entity.SchoolClassEntity;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;

@ExtendWith(MockitoExtension.class)
public class RegisterSchoolClassUseCaseTest {
    @Mock
    SchoolClassRepository schoolClassRepository;

    @Mock
    SchoolClassEventPublisher<SchoolClassRegisteredEvent> schoolClassEventPublisher;

    @InjectMocks
    RegisterSchoolClassUseCase registerSchoolClassUsecase;

    @Test
    public void givenValidSchoolClass_whenRegister_thenShouldPersistAndPublishEvent() {
        // given
        RegisterSchoolClassCommand registerSchoolClassCommand = new RegisterSchoolClassCommand(
                "Class",
                "KINDERGARTEN",
                2026,
                "ACTIVE");

        // when
        when(schoolClassRepository.save(any())).thenReturn(null);
        doNothing().when(schoolClassEventPublisher).publish(any());
        UUID schoolClassId = registerSchoolClassUsecase.execute(registerSchoolClassCommand);

        // then
        verify(schoolClassRepository, times(1)).save(any(SchoolClassEntity.class));
        verify(schoolClassEventPublisher, times(1)).publish(any(SchoolClassRegisteredEvent.class));
        assertNotNull(schoolClassId);
    }
}
