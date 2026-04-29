package br.nom.corbal.denison.elunari.registration.application.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.nom.corbal.denison.elunari.registration.application.events.StudentEventPublisher;
import br.nom.corbal.denison.elunari.registration.application.events.StudentRegisteredEvent;
import br.nom.corbal.denison.elunari.registration.application.repository.StudentRepository;
import br.nom.corbal.denison.elunari.registration.application.usecases.command.RegisterStudentCommand;
import br.nom.corbal.denison.elunari.registration.domain.Student;

@ExtendWith(MockitoExtension.class)
public class RegisterStudentUsecaseTest {

        @Mock
        StudentRepository studentRepository;

        @Mock
        StudentEventPublisher<StudentRegisteredEvent> studentEventPublisher;

        @InjectMocks
        RegisterStudentUsecase registerStudentUsecase;

        @Test
        public void givenValidStudent_whenRegister_thenShouldPersistAndPublishEvent() {
                // given
                RegisterStudentCommand registerStudentCommand = new RegisterStudentCommand(
                                "First1",
                                "Last1",
                                LocalDate.now(),
                                List.of(new RegisterStudentCommand.GuardianDetails(
                                                "First2",
                                                "Last2",
                                                Set.of("(11)11111-1111"),
                                                Set.of("null@null.com"),
                                                "FATHER")),
                                "M",
                                "Brazilian",
                                "111.111.111-11",
                                "DF",
                                "Brasilia",
                                "Asa Sul",
                                "Quadra 1",
                                "11.111-111",
                                "1",
                                "ACTIVE");

                // when
                when(studentRepository.save(any())).thenReturn(null);
                doNothing().when(studentEventPublisher).publish(any());
                UUID studentId = registerStudentUsecase.execute(registerStudentCommand);

                // then
                verify(studentRepository, times(1)).save(any(Student.class));
                verify(studentEventPublisher, times(1)).publish(any(StudentRegisteredEvent.class));
                assertNotNull(studentId);
        }
}