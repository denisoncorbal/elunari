package br.nom.corbal.denison.elunari.academic.application.usecases;

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

import br.nom.corbal.denison.elunari.academic.application.events.subject.SubjectEventPublisher;
import br.nom.corbal.denison.elunari.academic.application.events.subject.SubjectRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.application.repository.SubjectRepository;
import br.nom.corbal.denison.elunari.academic.application.usecases.command.RegisterSubjectCommand;
import br.nom.corbal.denison.elunari.academic.domain.Subject;

@ExtendWith(MockitoExtension.class)
public class RegisterSubjectUsecaseTest {
    @Mock
    SubjectRepository subjectRepository;

    @Mock
    SubjectEventPublisher<SubjectRegisteredEvent> subjectEventPublisher;

    @InjectMocks
    RegisterSubjectUsecase registerSubjectUsecase;

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
        verify(subjectRepository, times(1)).save(any(Subject.class));
        verify(subjectEventPublisher, times(1)).publish(any(SubjectRegisteredEvent.class));
        assertNotNull(subjectId);
    }
}
