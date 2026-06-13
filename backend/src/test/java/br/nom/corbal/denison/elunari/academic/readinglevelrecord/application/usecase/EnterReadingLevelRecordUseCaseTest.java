package br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.usecase;

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
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.command.EnterReadingLevelRecordCommand;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.event.ReadingLevelRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.event.ReadingLevelRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.model.aggregate.ReadingLevelRecordAggregate;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.repository.ReadingLevelRecordRepository;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.TeacherGateway;

@ExtendWith(MockitoExtension.class)
public class EnterReadingLevelRecordUseCaseTest {
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
        ReadingLevelRecordRepository readingLevelRecordRepository;

        @Mock
        ReadingLevelRecordEventPublisher<ReadingLevelRecordEnteredEvent> readingLevelRecordEventPublisher;

        @InjectMocks
        EnterReadingLevelRecordUseCase enterReadingLevelRecordUseCase;

        private EnterReadingLevelRecordCommand validEnterReadingLevelRecordCommand() {
                return new EnterReadingLevelRecordCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                "aa");
        }

        @Test
        public void givenValidReadingLevelRecord_whenEnter_thenShouldPersistAndPublishEvent() {
                // given
                EnterReadingLevelRecordCommand enterReadingLevelRecordCommand = validEnterReadingLevelRecordCommand();

                // when
                when(readingLevelRecordRepository.save(any())).thenReturn(null);
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(allocationRepository.isTeacherAllocatedToSchoolClass(any(), any())).thenReturn(true);
                when(enrollmentRepository.isStudentEnrolledToSchoolClass(any(), any())).thenReturn(true);

                doNothing().when(readingLevelRecordEventPublisher).publish(any());

                UUID readingLevelRecordId = enterReadingLevelRecordUseCase
                                .execute(enterReadingLevelRecordCommand);

                // then
                verify(readingLevelRecordRepository, times(1))
                                .save(any(ReadingLevelRecordAggregate.class));
                verify(teacherGateway, times(1)).existsById(any(UUID.class));
                verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
                verify(studentGateway, times(1)).existsById(any(UUID.class));
                verify(allocationRepository, times(1)).isTeacherAllocatedToSchoolClass(any(UUID.class),
                                any(UUID.class));
                verify(enrollmentRepository, times(1)).isStudentEnrolledToSchoolClass(any(UUID.class),
                                any(UUID.class));
                verify(readingLevelRecordEventPublisher, times(1))
                                .publish(any(ReadingLevelRecordEnteredEvent.class));

                assertNotNull(readingLevelRecordId);
        }

        @Test
        public void givenTeacherIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
                // given
                EnterReadingLevelRecordCommand enterReadingLevelRecordCommand = validEnterReadingLevelRecordCommand();

                // when
                when(teacherGateway.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> enterReadingLevelRecordUseCase
                                .execute(enterReadingLevelRecordCommand));
        }

        @Test
        public void givenStudentIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
                // given
                EnterReadingLevelRecordCommand enterReadingLevelRecordCommand = validEnterReadingLevelRecordCommand();

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> enterReadingLevelRecordUseCase
                                .execute(enterReadingLevelRecordCommand));
        }

        @Test
        public void givenSchoolClassIdThatDoesNotExist_whenEnter_thenShouldThrowException() {
                // given
                EnterReadingLevelRecordCommand enterReadingLevelRecordCommand = validEnterReadingLevelRecordCommand();

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> enterReadingLevelRecordUseCase
                                .execute(enterReadingLevelRecordCommand));
        }

        @Test
        public void givenInvalidReadingLevelRecord_whenEnter_thenShouldThrowException() {
                // given
                EnterReadingLevelRecordCommand enterReadingLevelRecordCommand = validEnterReadingLevelRecordCommand();

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
                        assertThrows(IllegalArgumentException.class, () -> enterReadingLevelRecordUseCase
                                        .execute(enterReadingLevelRecordCommand));
                        ;
                }
        }
}
