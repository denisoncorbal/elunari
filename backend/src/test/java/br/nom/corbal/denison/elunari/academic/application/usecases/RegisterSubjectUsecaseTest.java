package br.nom.corbal.denison.elunari.academic.application.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import javax.security.auth.Subject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
