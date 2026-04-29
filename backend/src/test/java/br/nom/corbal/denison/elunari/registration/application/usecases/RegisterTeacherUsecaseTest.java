package br.nom.corbal.denison.elunari.registration.application.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.nom.corbal.denison.elunari.registration.application.events.TeacherEventPublisher;
import br.nom.corbal.denison.elunari.registration.application.events.TeacherRegisteredEvent;
import br.nom.corbal.denison.elunari.registration.application.repository.TeacherRepository;
import br.nom.corbal.denison.elunari.registration.application.usecases.command.RegisterTeacherCommand;
import br.nom.corbal.denison.elunari.registration.domain.Teacher;

@ExtendWith(MockitoExtension.class)
public class RegisterTeacherUsecaseTest {
    @Mock
    TeacherRepository teacherRepository;

    @Mock
    TeacherEventPublisher<TeacherRegisteredEvent> teacherEventPublisher;

    @InjectMocks
    RegisterTeacherUsecase registerTeacherUsecase;

    @Test
    public void givenValidTeacher_whenRegister_thenShouldPersistAndPublishEvent() {
        // given
        RegisterTeacherCommand registerTeacherCommand = new RegisterTeacherCommand(
                "First1",
                "Last1",
                LocalDate.now(),
                "M",
                "Brazilian",
                "111.111.111-11",
                "DF",
                "Brasilia",
                "Asa Sul",
                "Quadra 1",
                "11.111-111",
                "1",
                Set.of("(11)11111-1111"),
                Set.of("null@null.com"),
                "ACTIVE");

        // when
        when(teacherRepository.save(any())).thenReturn(null);
        doNothing().when(teacherEventPublisher).publish(any());
        UUID teacherId = registerTeacherUsecase.execute(registerTeacherCommand);

        // then
        verify(teacherRepository, times(1)).save(any(Teacher.class));
        verify(teacherEventPublisher, times(1)).publish(any(TeacherRegisteredEvent.class));
        assertNotNull(teacherId);
    }
}
