package br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.usecase;

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
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.command.EnterEnglishLanguageDevelopmentRecordCommand;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.event.EnglishLanguageDevelopmentRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.event.EnglishLanguageDevelopmentRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.model.aggregate.EnglishLanguageDevelopmentRecordAggregate;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.repository.EnglishLanguageDevelopmentRecordRepository;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.TeacherGateway;

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

        private EnterEnglishLanguageDevelopmentRecordCommand validEnglishLanguageDevelopmentRecord() {
                return new EnterEnglishLanguageDevelopmentRecordCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                "LEVEL 1");
        }

        @Test
        public void givenValidEnglishLanguageDevelopmentRecord_whenEnter_thenShouldPersistAndPublishEvent() {
                // given
                EnterEnglishLanguageDevelopmentRecordCommand enterEnglishLanguageDevelopmentRecordCommand = validEnglishLanguageDevelopmentRecord();

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
        public void givenTeacherIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
                // given
                EnterEnglishLanguageDevelopmentRecordCommand enterEnglishLanguageDevelopmentRecordCommand = validEnglishLanguageDevelopmentRecord();

                // when
                when(teacherGateway.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> enterEnglishLanguageDevelopmentRecordUseCase
                                .execute(enterEnglishLanguageDevelopmentRecordCommand));
        }

        @Test
        public void givenStudentIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
                // given
                EnterEnglishLanguageDevelopmentRecordCommand enterEnglishLanguageDevelopmentRecordCommand = validEnglishLanguageDevelopmentRecord();

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> enterEnglishLanguageDevelopmentRecordUseCase
                                .execute(enterEnglishLanguageDevelopmentRecordCommand));
        }

        @Test
        public void givenSchoolClassIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
                // given
                EnterEnglishLanguageDevelopmentRecordCommand enterEnglishLanguageDevelopmentRecordCommand = validEnglishLanguageDevelopmentRecord();

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> enterEnglishLanguageDevelopmentRecordUseCase
                                .execute(enterEnglishLanguageDevelopmentRecordCommand));
        }

        @Test
        public void givenInvalidEnglishLanguageDevelopmentRecord_whenEnter_thenShouldThrowException() {
                // given
                EnterEnglishLanguageDevelopmentRecordCommand enterEnglishLanguageDevelopmentRecordCommand = validEnglishLanguageDevelopmentRecord();

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
