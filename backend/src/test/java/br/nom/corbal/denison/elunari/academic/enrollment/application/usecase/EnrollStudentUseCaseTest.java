package br.nom.corbal.denison.elunari.academic.enrollment.application.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import br.nom.corbal.denison.elunari.academic.enrollment.application.command.EnrollStudentCommand;
import br.nom.corbal.denison.elunari.academic.enrollment.application.event.EnrollmentEventPublisher;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.event.StudentEnrolledEvent;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.model.aggregate.EnrollmentAggregate;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;

@ExtendWith(MockitoExtension.class)
public class EnrollStudentUseCaseTest {
    @Mock
    SchoolClassRepository schoolClassRepository;

    @Mock
    StudentGateway studentGateway;

    @Mock
    EnrollmentRepository enrollmentRepository;

    @Mock
    EnrollmentEventPublisher<StudentEnrolledEvent> enrollmentEventPublisher;

    @InjectMocks
    EnrollStudentUseCase enrollStudentUseCase;

    @Test
    public void givenValidEnrollment_whenEnroll_thenShouldPersistAndPublishEvent() {
        // given
        EnrollStudentCommand enrollStudentCommand = new EnrollStudentCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "ACTIVE");

        // when
        when(enrollmentRepository.save(any())).thenReturn(null);
        when(studentGateway.existsById(any())).thenReturn(true);
        when(schoolClassRepository.existsById(any())).thenReturn(true);
        when(enrollmentRepository.isStudentAlreadyEnrolled(any()))
                .thenReturn(false);
        doNothing().when(enrollmentEventPublisher).publish(any());
        UUID enrollmentId = enrollStudentUseCase.execute(enrollStudentCommand);

        // then
        verify(enrollmentRepository, times(1)).save(any(EnrollmentAggregate.class));
        verify(enrollmentRepository, times(1)).isStudentAlreadyEnrolled(any(UUID.class));
        verify(studentGateway, times(1)).existsById(any(UUID.class));
        verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
        verify(enrollmentEventPublisher, times(1)).publish(any(StudentEnrolledEvent.class));
        assertNotNull(enrollmentId);
    }

    @Test
    public void givenInvalidEnrollment_whenEnroll_thenShouldThrowException() {
        // given
        EnrollStudentCommand enrollStudentCommand = new EnrollStudentCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "ACTIVE");

        // when
        when(studentGateway.existsById(any())).thenReturn(true);
        when(schoolClassRepository.existsById(any())).thenReturn(true);
        when(enrollmentRepository.isStudentAlreadyEnrolled(any()))
                .thenReturn(true);
        // then

        assertThrows(IllegalArgumentException.class, () -> {
            enrollStudentUseCase.execute(enrollStudentCommand);
        });

        verify(enrollmentRepository, times(1)).isStudentAlreadyEnrolled(any(UUID.class));
        verify(studentGateway, times(1)).existsById(any(UUID.class));
        verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
    }
}
