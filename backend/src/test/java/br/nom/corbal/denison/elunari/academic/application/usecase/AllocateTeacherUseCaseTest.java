package br.nom.corbal.denison.elunari.academic.application.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
    public void givenValidSchoolClass_whenRegister_thenShouldPersistAndPublishEvent() {
        // given
        AllocateTeacherCommand allocateTeacherCommand = new AllocateTeacherCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.now().toLocalTime(),
                LocalDateTime.now().toLocalTime().plusMinutes(10));

        // when
        when(allocationRepository.save(any())).thenReturn(null);
        when(teacherGateway.existsById(any())).thenReturn(true);
        when(schoolClassRepository.existsById(any())).thenReturn(true);
        when(subjectRepository.existsById(any())).thenReturn(true);
        doNothing().when(allocationEventPublisher).publish(any());
        UUID allocationId = allocateTeacherUseCase.execute(allocateTeacherCommand);

        // then
        verify(allocationRepository, times(1)).save(any(AllocationAggregate.class));
        verify(teacherGateway, times(1)).existsById(any(UUID.class));
        verify(schoolClassRepository, times(1)).existsById(any(UUID.class));
        verify(subjectRepository, times(1)).existsById(any(UUID.class));
        verify(allocationEventPublisher, times(1)).publish(any(TeacherAllocatedEvent.class));
        assertNotNull(allocationId);
    }
}
