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

import br.nom.corbal.denison.elunari.academic.application.command.RegisterSubjectCommand;
import br.nom.corbal.denison.elunari.academic.application.event.subject.SubjectEventPublisher;
import br.nom.corbal.denison.elunari.academic.domain.event.SubjectRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.model.entity.SubjectEntity;
import br.nom.corbal.denison.elunari.academic.domain.repository.SubjectRepository;

@ExtendWith(MockitoExtension.class)
public class RegisterSubjectUseCaseTest {
    @Mock
    SubjectRepository subjectRepository;

    @Mock
    SubjectEventPublisher<SubjectRegisteredEvent> subjectEventPublisher;

    @InjectMocks
    RegisterSubjectUseCase registerSubjectUsecase;

    @Test
    public void givenValidSubject_whenRegister_thenShouldPersistAndPublishEvent() {
        // given
        RegisterSubjectCommand registerSubjectCommand = new RegisterSubjectCommand(
                "Subject",
                "ACTIVE");

        // when
        when(subjectRepository.save(any())).thenReturn(null);
        doNothing().when(subjectEventPublisher).publish(any());
        UUID subjectId = registerSubjectUsecase.execute(registerSubjectCommand);

        // then
        verify(subjectRepository, times(1)).save(any(SubjectEntity.class));
        verify(subjectEventPublisher, times(1)).publish(any(SubjectRegisteredEvent.class));
        assertNotNull(subjectId);
    }
}
