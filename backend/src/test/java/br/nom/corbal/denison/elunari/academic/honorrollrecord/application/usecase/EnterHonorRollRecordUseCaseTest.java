package br.nom.corbal.denison.elunari.academic.honorrollrecord.application.usecase;

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

import br.nom.corbal.denison.elunari.academic.allocation.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.application.command.EnterHonorRollRecordCommand;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.application.event.HonorRollRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.event.HonorRollRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.model.aggregate.HonorRollRecordAggregate;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.repository.HonorRollRecordRepository;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.TeacherGateway;

@ExtendWith(MockitoExtension.class)
public class EnterHonorRollRecordUseCaseTest {
    @Mock
    SchoolClassRepository schoolClassRepository;

    @Mock
    TeacherGateway teacherGateway;

    @Mock
    StudentGateway studentGateway;

    @Mock
    AllocationRepository allocationRepository;

    @Mock
    EnrollmentRepository enrollmentRepository;

    @Mock
    HonorRollRecordRepository honorRollRecordRepository;

    @Mock
    HonorRollRecordEventPublisher<HonorRollRecordEnteredEvent> honorRollRecordEventPublisher;

    @InjectMocks
    EnterHonorRollRecordUseCase enterHonorRollRecordUseCase;

    private EnterHonorRollRecordCommand validEnterHonorRollRecordCommand() {
        return new EnterHonorRollRecordCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "HONOR ROLL");
    }

    @Test
    public void givenValidHonorRollRecord_whenEnter_thenShouldPersistAndPublishEvent() {
        // given
        EnterHonorRollRecordCommand enterHonorRollRecordCommand = validEnterHonorRollRecordCommand();

        // when
        when(honorRollRecordRepository.save(any())).thenReturn(null);
        when(teacherGateway.existsById(any())).thenReturn(true);
        when(schoolClassRepository.existsById(any())).thenReturn(true);
        when(studentGateway.existsById(any())).thenReturn(true);
        when(allocationRepository.isTeacherAllocatedToSchoolClass(any(), any())).thenReturn(true);
        when(enrollmentRepository.isStudentEnrolledToSchoolClass(any(), any())).thenReturn(true);

        doNothing().when(honorRollRecordEventPublisher).publish(any());

        UUID honorRollRecordId = enterHonorRollRecordUseCase
                .execute(enterHonorRollRecordCommand);

        // then
        verify(honorRollRecordRepository, times(1))
                .save(any(HonorRollRecordAggregate.class));
        verify(teacherGateway, times(1)).existsById(any(UUID.class));
        verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
        verify(studentGateway, times(1)).existsById(any(UUID.class));
        verify(allocationRepository, times(1)).isTeacherAllocatedToSchoolClass(any(UUID.class),
                any(UUID.class));
        verify(enrollmentRepository, times(1)).isStudentEnrolledToSchoolClass(any(UUID.class),
                any(UUID.class));
        verify(honorRollRecordEventPublisher, times(1))
                .publish(any(HonorRollRecordEnteredEvent.class));

        assertNotNull(honorRollRecordId);
    }

    @Test
    public void givenTeacherIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
        // given
        EnterHonorRollRecordCommand enterHonorRollRecordCommand = validEnterHonorRollRecordCommand();

        // when
        when(teacherGateway.existsById(any())).thenReturn(false);

        // then
        assertThrows(IllegalArgumentException.class, () -> enterHonorRollRecordUseCase
                .execute(enterHonorRollRecordCommand));
    }

    @Test
    public void givenStudentIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
        // given
        EnterHonorRollRecordCommand enterHonorRollRecordCommand = validEnterHonorRollRecordCommand();

        // when
        when(teacherGateway.existsById(any())).thenReturn(true);
        when(studentGateway.existsById(any())).thenReturn(false);

        // then
        assertThrows(IllegalArgumentException.class, () -> enterHonorRollRecordUseCase
                .execute(enterHonorRollRecordCommand));
    }

    @Test
    public void givenSchoolClassIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
        // given
        EnterHonorRollRecordCommand enterHonorRollRecordCommand = validEnterHonorRollRecordCommand();

        // when
        when(teacherGateway.existsById(any())).thenReturn(true);
        when(studentGateway.existsById(any())).thenReturn(true);
        when(schoolClassRepository.existsById(any())).thenReturn(false);

        // then
        assertThrows(IllegalArgumentException.class, () -> enterHonorRollRecordUseCase
                .execute(enterHonorRollRecordCommand));
    }

    @Test
    public void givenInvalidHonorRollRecord_whenEnter_thenShouldThrowException() {
        // given
        EnterHonorRollRecordCommand enterHonorRollRecordCommand = validEnterHonorRollRecordCommand();

        // when
        when(teacherGateway.existsById(any())).thenReturn(true);
        when(schoolClassRepository.existsById(any())).thenReturn(true);
        when(studentGateway.existsById(any())).thenReturn(true);
        when(allocationRepository.isTeacherAllocatedToSchoolClass(any(), any())).thenReturn(false)
                .thenReturn(true);
        when(enrollmentRepository.isStudentEnrolledToSchoolClass(any(), any())).thenReturn(false)
                .thenReturn(false).thenReturn(true);

        // then
        for (int i = 0; i < 3; i++) {
            assertThrows(IllegalArgumentException.class, () -> enterHonorRollRecordUseCase
                    .execute(enterHonorRollRecordCommand));
            ;
        }
    }
}
