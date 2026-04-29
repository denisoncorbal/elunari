package br.nom.corbal.denison.elunari.registration.application.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class RegisterStudentUsecaseTest {

    StudentRepository studentRepository = mock(StudentRepository.class);

    StudentEventPublisher studentEventPublisher = mock(StudentEventPublisher.class);

    RegisterStudentUsecase RegisterStudentUsecase = new RegisterStudentUsecase(studentRepository,
            studentEventPublisher);

    @Test
    public void givenValidStudent_whenRegister_thenShouldPersistAndPublishEvent() {
        // given
        RegisterStudentCommand registerStudentCommand = new RegisterStudentCommand();

        // when
        registerStudentUsecase.execute(registerStudentCommand);

        // then
        verify(studentRepository, times(1)).save(any(Student.class));
        verify(studentEventPublisher, times(1)).publish(any(StudentRegisteredEvent.class));
    }
}