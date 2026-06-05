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

import br.nom.corbal.denison.elunari.academic.application.command.RegisterAssignementCommand;
import br.nom.corbal.denison.elunari.academic.application.event.assignement.AssignementEventPublisher;
import br.nom.corbal.denison.elunari.academic.domain.event.AssignementRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.AssignementAggregate;
import br.nom.corbal.denison.elunari.academic.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.AssignementRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SubjectRepository;

@ExtendWith(MockitoExtension.class)
public class RegisterAssignementUseCaseTest {

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
        SubjectRepository subjectRepository;

        @Mock
        AssignementRepository assignementRepository;

        @Mock
        AssignementEventPublisher<AssignementRegisteredEvent> assignementEventPublisher;

        @InjectMocks
        RegisterAssignementUseCase registerAssignementUseCase;

        private RegisterAssignementCommand validAssignement() {
                return new RegisterAssignementCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                "FIRST",
                                "TEST",
                                1,
                                0,
                                "ACTIVE");
        }

        @Test
        public void givenValidAssignement_whenRegister_thenShouldPersistAndPublishEvent() {
                // given
                RegisterAssignementCommand registerAssignementCommand = validAssignement();

                // when
                when(assignementRepository.save(any())).thenReturn(null);
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(subjectRepository.existsById(any())).thenReturn(true);
                when(allocationRepository.isTeacherAllocatedToSchoolClassAndSubject(any(), any(), any()))
                                .thenReturn(true);
                when(enrollmentRepository.isStudentEnrolledToSchoolClass(any(), any())).thenReturn(true);

                doNothing().when(assignementEventPublisher).publish(any());

                UUID specialEducationStudentRecordId = registerAssignementUseCase
                                .execute(registerAssignementCommand);

                // then
                verify(assignementRepository, times(1))
                                .save(any(AssignementAggregate.class));
                verify(teacherGateway, times(1)).existsById(any(UUID.class));
                verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
                verify(studentGateway, times(1)).existsById(any(UUID.class));
                verify(subjectRepository, times(1)).existsById(any(UUID.class));
                verify(allocationRepository, times(1)).isTeacherAllocatedToSchoolClassAndSubject(any(UUID.class),
                                any(UUID.class),
                                any(UUID.class));
                verify(enrollmentRepository, times(1)).isStudentEnrolledToSchoolClass(any(UUID.class),
                                any(UUID.class));
                verify(assignementEventPublisher, times(1))
                                .publish(any(AssignementRegisteredEvent.class));

                assertNotNull(specialEducationStudentRecordId);
        }

        @Test
        public void givenTeacherIdThatDoesNotExist_whenRegister_thenShouldThrowException() {
                // given
                RegisterAssignementCommand registerAssignementCommand = validAssignement();

                // when
                when(teacherGateway.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> registerAssignementUseCase
                                .execute(registerAssignementCommand));
        }

        @Test
        public void givenStudentIdThatDoesNotExist_whenRegister_thenShouldThrowException() {
                // given
                RegisterAssignementCommand registerAssignementCommand = validAssignement();

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> registerAssignementUseCase
                                .execute(registerAssignementCommand));
        }

        @Test
        public void givenSchoolClassIdThatDoesNotExist_whenRegister_thenShouldThrowException() {
                // given
                RegisterAssignementCommand registerAssignementCommand = validAssignement();

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> registerAssignementUseCase
                                .execute(registerAssignementCommand));
        }

        @Test
        public void givenSubjectIdThatDoesNotExist_whenRegister_thenShouldThrowException() {
                // given
                RegisterAssignementCommand registerAssignementCommand = validAssignement();

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(subjectRepository.existsById(any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> registerAssignementUseCase
                                .execute(registerAssignementCommand));
        }

        @Test
        public void givenTeacherNotAllocatedToSchoolClassAndSubject_whenRegister_thenShouldThrowException() {
                // given
                RegisterAssignementCommand registerAssignementCommand = validAssignement();

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(subjectRepository.existsById(any())).thenReturn(true);
                when(allocationRepository.isTeacherAllocatedToSchoolClassAndSubject(any(), any(), any()))
                                .thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> registerAssignementUseCase
                                .execute(registerAssignementCommand));
        }

        @Test
        public void givenStudentNotEnrolledToSchoolClass_whenRegister_thenShouldThrowException() {
                // given
                RegisterAssignementCommand registerAssignementCommand = validAssignement();

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(studentGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(subjectRepository.existsById(any())).thenReturn(true);
                when(allocationRepository.isTeacherAllocatedToSchoolClassAndSubject(any(), any(), any()))
                                .thenReturn(true);
                when(enrollmentRepository.isStudentEnrolledToSchoolClass(any(), any())).thenReturn(false);

                // then
                assertThrows(IllegalArgumentException.class, () -> registerAssignementUseCase
                                .execute(registerAssignementCommand));
        }
}
