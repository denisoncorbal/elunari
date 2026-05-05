package br.nom.corbal.denison.elunari.academic.application.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.nom.corbal.denison.elunari.academic.application.command.AllocateTeacherCommand;
import br.nom.corbal.denison.elunari.academic.application.event.allocation.AllocationEventPublisher;
import br.nom.corbal.denison.elunari.academic.domain.event.TeacherAllocatedEvent;
import br.nom.corbal.denison.elunari.academic.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.AllocationAggregate;
import br.nom.corbal.denison.elunari.academic.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SubjectRepository;

@ExtendWith(MockitoExtension.class)
public class AllocateTeacherUseCaseTest {
        @Mock
        SchoolClassRepository schoolClassRepository;

        @Mock
        SubjectRepository subjectRepository;

        @Mock
        TeacherGateway teacherGateway;

        @Mock
        AllocationRepository allocationRepository;

        @Mock
        AllocationEventPublisher<TeacherAllocatedEvent> allocationEventPublisher;

        @InjectMocks
        AllocateTeacherUseCase allocateTeacherUseCase;

        @Test
        public void givenValidAllocation_whenAllocate_thenShouldPersistAndPublishEvent() {
                // given
                Random random = new Random();
                int baseTestHour = random.nextInt(10) + 1;

                AllocateTeacherCommand allocateTeacherCommand = new AllocateTeacherCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                LocalTime.of(baseTestHour, 30),
                                LocalTime.of(baseTestHour, 40));

                // when
                when(allocationRepository.save(any())).thenReturn(null);
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(subjectRepository.existsById(any())).thenReturn(true);
                when(allocationRepository.findAllByTeacherIdAndStatusActive(any()))
                                .thenReturn(Set.of(
                                                new AllocationAggregate(null, null, null,
                                                                LocalTime.of(baseTestHour, 10),
                                                                LocalTime.of(baseTestHour, 20)),
                                                new AllocationAggregate(null, null, null,
                                                                LocalTime.of(baseTestHour, 50),
                                                                LocalTime.of(baseTestHour, 59))));
                doNothing().when(allocationEventPublisher).publish(any());
                UUID allocationId = allocateTeacherUseCase.execute(allocateTeacherCommand);

                // then
                verify(allocationRepository, times(1)).save(any(AllocationAggregate.class));
                verify(allocationRepository, times(1)).findAllByTeacherIdAndStatusActive(any(UUID.class));
                verify(teacherGateway, times(1)).existsById(any(UUID.class));
                verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
                verify(subjectRepository, times(1)).existsById(any(UUID.class));
                verify(allocationEventPublisher, times(1)).publish(any(TeacherAllocatedEvent.class));
                assertNotNull(allocationId);
        }

        @Test
        public void givenInvalidAllocation_whenAllocate_thenShouldThrowException() {
                // given
                AllocateTeacherCommand allocateTeacherCommand = new AllocateTeacherCommand(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                LocalTime.of(8, 0),
                                LocalTime.of(15, 0));

                // when
                when(teacherGateway.existsById(any())).thenReturn(true);
                when(schoolClassRepository.existsById(any())).thenReturn(true);
                when(subjectRepository.existsById(any())).thenReturn(true);
                when(allocationRepository.findAllByTeacherIdAndStatusActive(any()))
                                .thenReturn(Set.<AllocationAggregate>of(
                                                new AllocationAggregate(UUID.randomUUID(), UUID.randomUUID(),
                                                                UUID.randomUUID(),
                                                                LocalTime.of(7, 0), LocalTime.of(9, 0))))
                                .thenReturn(Set.<AllocationAggregate>of(
                                                new AllocationAggregate(UUID.randomUUID(), UUID.randomUUID(),
                                                                UUID.randomUUID(),
                                                                LocalTime.of(9, 0), LocalTime.of(10, 0))))
                                .thenReturn(Set.<AllocationAggregate>of(
                                                new AllocationAggregate(UUID.randomUUID(), UUID.randomUUID(),
                                                                UUID.randomUUID(),
                                                                LocalTime.of(12, 0), LocalTime.of(17, 0))))
                                .thenReturn(Set.<AllocationAggregate>of(
                                                new AllocationAggregate(UUID.randomUUID(), UUID.randomUUID(),
                                                                UUID.randomUUID(),
                                                                LocalTime.of(7, 0), LocalTime.of(17, 0))));

                // then
                for (int i = 0; i < 4; i++) {
                        assertThrows(IllegalArgumentException.class, () -> {
                                allocateTeacherUseCase.execute(allocateTeacherCommand);
                        });
                }
        }
}
