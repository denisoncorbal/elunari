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

import br.nom.corbal.denison.elunari.academic.application.command.EnterEnglishLanguageDevelopmentRecordCommand;
import br.nom.corbal.denison.elunari.academic.application.event.englishlanguagedevelopmentrecord.EnglishLanguageDevelopmentRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.domain.event.EnglishLanguageDevelopmentRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.EnglishLanguageDevelopmentRecordAggregate;
import br.nom.corbal.denison.elunari.academic.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.EnglishLanguageDevelopmentRecordRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;

@ExtendWith(MockitoExtension.class)
public class EnterEnglishLanguageDevelopmentRecordUseCaseTest {
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
        EnglishLanguageDevelopmentRecordRepository englishLanguageDevelopmentRecordRepository;

        @Mock
        EnglishLanguageDevelopmentRecordEventPublisher<EnglishLanguageDevelopmentRecordEnteredEvent> englishLanguageDevelopmentRecordEventPublisher;

        @InjectMocks
        EnterEnglishLanguageDevelopmentRecordUseCase enterEnglishLanguageDevelopmentRecordUseCase;

        @Test
        public void givenValidEnglishLanguageDevelopmentRecord_whenEnter_thenShouldPersistAndPublishEvent() {
                // given
                EnterEnglishLanguageDevelopmentRecordCommand enterEnglishLanguageDevelopmentRecordCommand = new EnterEnglishLanguageDevelopmentRecordCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                "LEVEL 1");

                // when
                when(englishLanguageDevelopmentRecordRepository.save(any())).thenReturn(null);
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(allocationRepository.isTeacherAllocatedToSchoolClass(any(), any())).thenReturn(true);
                when(enrollmentRepository.isStudentEnrolledToSchoolClass(any(), any())).thenReturn(true);

                doNothing().when(englishLanguageDevelopmentRecordEventPublisher).publish(any());

                UUID englishLanguageDevelopmentRecordId = enterEnglishLanguageDevelopmentRecordUseCase
                                .execute(enterEnglishLanguageDevelopmentRecordCommand);

                // then
                verify(englishLanguageDevelopmentRecordRepository, times(1))
                                .save(any(EnglishLanguageDevelopmentRecordAggregate.class));
                verify(teacherGateway, times(1)).existsById(any(UUID.class));
                verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
                verify(studentGateway, times(1)).existsById(any(UUID.class));
                verify(allocationRepository, times(1)).isTeacherAllocatedToSchoolClass(any(UUID.class),
                                any(UUID.class));
                verify(enrollmentRepository, times(1)).isStudentEnrolledToSchoolClass(any(UUID.class),
                                any(UUID.class));
                verify(englishLanguageDevelopmentRecordEventPublisher, times(1))
                                .publish(any(EnglishLanguageDevelopmentRecordEnteredEvent.class));

                assertNotNull(englishLanguageDevelopmentRecordId);
        }

        @Test
        public void givenInvalidEnglishLanguageDevelopmentRecord_whenEnter_thenShouldThrowException() {
                // given
                EnterEnglishLanguageDevelopmentRecordCommand enterEnglishLanguageDevelopmentRecordCommand = new EnterEnglishLanguageDevelopmentRecordCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                "LEVEL 1");

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
                        assertThrows(IllegalArgumentException.class, () -> enterEnglishLanguageDevelopmentRecordUseCase
                                        .execute(enterEnglishLanguageDevelopmentRecordCommand));
                        ;
                }
        }
}
