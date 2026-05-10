package br.nom.corbal.denison.elunari.academic.application.usecase;

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

import br.nom.corbal.denison.elunari.academic.application.command.EnterSpecialEducationStudentRecordCommand;
import br.nom.corbal.denison.elunari.academic.application.event.specialeducationstudentrecord.SpecialEducationStudentRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.domain.event.SpecialEducationStudentRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.SpecialEducationStudentRecordAggregate;
import br.nom.corbal.denison.elunari.academic.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SpecialEducationStudentRecordRepository;

@ExtendWith(MockitoExtension.class)
public class EnterSpecialEducationStudentRecordUseCaseTest {
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
        SpecialEducationStudentRecordRepository specialEducationStudentRecordRepository;

        @Mock
        SpecialEducationStudentRecordEventPublisher<SpecialEducationStudentRecordEnteredEvent> specialEducationStudentRecordEventPublisher;

        @InjectMocks
        EnterSpecialEducationStudentRecordUseCase enterSpecialEducationStudentRecordUseCase;

        @Test
        public void givenValidSpecialEducationStudentRecord_whenEnter_thenShouldPersistAndPublishEvent() {
                // given
                EnterSpecialEducationStudentRecordCommand enterSpecialEducationStudentRecordCommand = new EnterSpecialEducationStudentRecordCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                "RED FLAG",
                                "ACTIVE");

                // when
                when(specialEducationStudentRecordRepository.save(any())).thenReturn(null);
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(allocationRepository.isTeacherAllocatedToSchoolClass(any(), any())).thenReturn(true);
                when(enrollmentRepository.isStudentEnrolledToSchoolClass(any(), any())).thenReturn(true);

                doNothing().when(specialEducationStudentRecordEventPublisher).publish(any());

                UUID specialEducationStudentRecordId = enterSpecialEducationStudentRecordUseCase
                                .execute(enterSpecialEducationStudentRecordCommand);

                // then
                verify(specialEducationStudentRecordRepository, times(1))
                                .save(any(SpecialEducationStudentRecordAggregate.class));
                verify(teacherGateway, times(1)).existsById(any(UUID.class));
                verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
                verify(studentGateway, times(1)).existsById(any(UUID.class));
                verify(allocationRepository, times(1)).isTeacherAllocatedToSchoolClass(any(UUID.class),
                                any(UUID.class));
                verify(enrollmentRepository, times(1)).isStudentEnrolledToSchoolClass(any(UUID.class),
                                any(UUID.class));
                verify(specialEducationStudentRecordEventPublisher, times(1))
                                .publish(any(SpecialEducationStudentRecordEnteredEvent.class));

                assertNotNull(specialEducationStudentRecordId);
        }

        @Test
        public void givenInvalidSpecialEducationStudentRecord_whenEnter_thenShouldThrowException() {
                // given
                EnterSpecialEducationStudentRecordCommand enterSpecialEducationStudentRecordCommand = new EnterSpecialEducationStudentRecordCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                "RED FLAG",
                                "ACTIVE");

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
                        assertThrows(IllegalArgumentException.class, () -> enterSpecialEducationStudentRecordUseCase
                                        .execute(enterSpecialEducationStudentRecordCommand));
                        ;
                }
        }
}
