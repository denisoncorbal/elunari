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

import br.nom.corbal.denison.elunari.academic.application.command.EnterResponseToInterventionRecordCommand;
import br.nom.corbal.denison.elunari.academic.application.event.responsetointerventionrecord.ResponseToInterventionRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.domain.event.ResponseToInterventionRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.ResponseToInterventionRecordAggregate;
import br.nom.corbal.denison.elunari.academic.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.ResponseToInterventionRecordRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;

@ExtendWith(MockitoExtension.class)
public class EnterResponseToInterventionRecordUseCaseTest {
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
        ResponseToInterventionRecordRepository responseToInterventionRecordRepository;

        @Mock
        ResponseToInterventionRecordEventPublisher<ResponseToInterventionRecordEnteredEvent> responseToInterventionRecordEventPublisher;

        @InjectMocks
        EnterResponseToInterventionRecordUseCase enterResponseToInterventionRecordUseCase;

        @Test
        public void givenValidResponseToInterventionRecord_whenEnter_thenShouldPersistAndPublishEvent() {
                // given
                EnterResponseToInterventionRecordCommand enterResponseToInterventionRecordCommand = new EnterResponseToInterventionRecordCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                "TIER 1",
                                "ACTIVE");

                // when
                when(responseToInterventionRecordRepository.save(any())).thenReturn(null);
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(allocationRepository.isTeacherAllocatedToSchoolClass(any(), any())).thenReturn(true);
                when(enrollmentRepository.isStudentEnrolledToSchoolClass(any(), any())).thenReturn(true);

                doNothing().when(responseToInterventionRecordEventPublisher).publish(any());

                UUID responseToInterventionRecordId = enterResponseToInterventionRecordUseCase
                                .execute(enterResponseToInterventionRecordCommand);

                // then
                verify(responseToInterventionRecordRepository, times(1))
                                .save(any(ResponseToInterventionRecordAggregate.class));
                verify(teacherGateway, times(1)).existsById(any(UUID.class));
                verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
                verify(studentGateway, times(1)).existsById(any(UUID.class));
                verify(allocationRepository, times(1)).isTeacherAllocatedToSchoolClass(any(UUID.class),
                                any(UUID.class));
                verify(enrollmentRepository, times(1)).isStudentEnrolledToSchoolClass(any(UUID.class),
                                any(UUID.class));
                verify(responseToInterventionRecordEventPublisher, times(1))
                                .publish(any(ResponseToInterventionRecordEnteredEvent.class));

                assertNotNull(responseToInterventionRecordId);
        }

        @Test
        public void givenInvalidResponseToInterventionRecord_whenEnter_thenShouldThrowException() {
                // given
                EnterResponseToInterventionRecordCommand enterResponseToInterventionRecordCommand = new EnterResponseToInterventionRecordCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                "TIER 1",
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
                        assertThrows(IllegalArgumentException.class, () -> enterResponseToInterventionRecordUseCase
                                        .execute(enterResponseToInterventionRecordCommand));
                        ;
                }
        }
}
