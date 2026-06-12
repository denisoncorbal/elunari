package br.nom.corbal.denison.elunari.academic.teachernoterecord.application.usecase;

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
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.application.command.EnterTeacherNoteRecordCommand;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.application.event.TeacherNoteRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.event.TeacherNoteRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.aggregate.TeacherNoteRecordAggregate;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.repository.TeacherNoteRecordRepository;

@ExtendWith(MockitoExtension.class)
public class EnterTeacherNoteRecordUseCaseTest {
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
    TeacherNoteRecordRepository teacherNoteRecordRepository;

    @Mock
    TeacherNoteRecordEventPublisher<TeacherNoteRecordEnteredEvent> teacherNoteRecordEventPublisher;

    @InjectMocks
    EnterTeacherNoteRecordUseCase enterTeacherNoteRecordUseCase;

    private EnterTeacherNoteRecordCommand validEnterTeacherNoteRecordCommand() {
        return new EnterTeacherNoteRecordCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Data",
                "ACTIVE");
    }

    @Test
    public void givenValidTeacherNoteRecord_whenEnter_thenShouldPersistAndPublishEvent() {
        // given
        EnterTeacherNoteRecordCommand enterTeacherNoteRecordCommand = validEnterTeacherNoteRecordCommand();

        // when
        when(teacherNoteRecordRepository.save(any())).thenReturn(null);
        when(teacherGateway.existsById(any())).thenReturn(true);
        when(schoolClassRepository.existsById(any())).thenReturn(true);
        when(studentGateway.existsById(any())).thenReturn(true);
        when(allocationRepository.isTeacherAllocatedToSchoolClass(any(), any())).thenReturn(true);
        when(enrollmentRepository.isStudentEnrolledToSchoolClass(any(), any())).thenReturn(true);

        doNothing().when(teacherNoteRecordEventPublisher).publish(any());

        UUID teacherNoteRecordId = enterTeacherNoteRecordUseCase
                .execute(enterTeacherNoteRecordCommand);

        // then
        verify(teacherNoteRecordRepository, times(1))
                .save(any(TeacherNoteRecordAggregate.class));
        verify(teacherGateway, times(1)).existsById(any(UUID.class));
        verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
        verify(studentGateway, times(1)).existsById(any(UUID.class));
        verify(allocationRepository, times(1)).isTeacherAllocatedToSchoolClass(any(UUID.class),
                any(UUID.class));
        verify(enrollmentRepository, times(1)).isStudentEnrolledToSchoolClass(any(UUID.class),
                any(UUID.class));
        verify(teacherNoteRecordEventPublisher, times(1))
                .publish(any(TeacherNoteRecordEnteredEvent.class));

        assertNotNull(teacherNoteRecordId);
    }

    @Test
    public void givenTeacherIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
        // given
        EnterTeacherNoteRecordCommand enterTeacherNoteRecordCommand = validEnterTeacherNoteRecordCommand();

        // when
        when(teacherGateway.existsById(any())).thenReturn(false);

        // then
        assertThrows(IllegalArgumentException.class, () -> enterTeacherNoteRecordUseCase
                .execute(enterTeacherNoteRecordCommand));
    }

    @Test
    public void givenStudentIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
        // given
        EnterTeacherNoteRecordCommand enterTeacherNoteRecordCommand = validEnterTeacherNoteRecordCommand();

        // when
        when(teacherGateway.existsById(any())).thenReturn(true);
        when(studentGateway.existsById(any())).thenReturn(false);

        // then
        assertThrows(IllegalArgumentException.class, () -> enterTeacherNoteRecordUseCase
                .execute(enterTeacherNoteRecordCommand));
    }

    @Test
    public void givenSchoolClassIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
        // given
        EnterTeacherNoteRecordCommand enterTeacherNoteRecordCommand = validEnterTeacherNoteRecordCommand();

        // when
        when(teacherGateway.existsById(any())).thenReturn(true);
        when(studentGateway.existsById(any())).thenReturn(true);
        when(schoolClassRepository.existsById(any())).thenReturn(false);

        // then
        assertThrows(IllegalArgumentException.class, () -> enterTeacherNoteRecordUseCase
                .execute(enterTeacherNoteRecordCommand));
    }

    @Test
    public void givenInvalidTeacherNoteRecord_whenEnter_thenShouldThrowException() {
        // given
        EnterTeacherNoteRecordCommand enterTeacherNoteRecordCommand = validEnterTeacherNoteRecordCommand();

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
            assertThrows(IllegalArgumentException.class, () -> enterTeacherNoteRecordUseCase
                    .execute(enterTeacherNoteRecordCommand));
            ;
        }
    }
}
