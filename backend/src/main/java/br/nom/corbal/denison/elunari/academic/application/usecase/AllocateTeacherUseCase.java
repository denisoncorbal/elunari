package br.nom.corbal.denison.elunari.academic.application.usecase;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.application.command.AllocateTeacherCommand;
import br.nom.corbal.denison.elunari.academic.application.event.allocation.AllocationEventPublisher;
import br.nom.corbal.denison.elunari.academic.application.mapper.AllocationMapper;
import br.nom.corbal.denison.elunari.academic.domain.event.TeacherAllocatedEvent;
import br.nom.corbal.denison.elunari.academic.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.AllocationAggregate;
import br.nom.corbal.denison.elunari.academic.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SubjectRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class AllocateTeacherUseCase implements BaseUseCase<UUID, AllocateTeacherCommand> {
    private final AllocationRepository allocationRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherGateway teacherGateway;
    private final AllocationEventPublisher<TeacherAllocatedEvent> allocationEventPublisher;

    public AllocateTeacherUseCase(AllocationRepository allocationRepository,
            SchoolClassRepository schoolClassRepository, SubjectRepository subjectRepository,
            TeacherGateway teacherGateway, AllocationEventPublisher<TeacherAllocatedEvent> allocationEventPublisher) {
        this.allocationRepository = allocationRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.subjectRepository = subjectRepository;
        this.teacherGateway = teacherGateway;
        this.allocationEventPublisher = allocationEventPublisher;
    }

    private final AllocationMapper allocationMapper = new AllocationMapper();

    @Override
    public UUID execute(AllocateTeacherCommand allocateTeacherCommand) {
        if (!this.teacherGateway.existsById(allocateTeacherCommand.teacherId())) {
            throw new IllegalArgumentException("Invalid teacher id");
        }
        if (!this.schoolClassRepository.existsById(allocateTeacherCommand.schoolClassId())) {
            throw new IllegalArgumentException("Invalid school class id");
        }
        if (!this.subjectRepository.existsById(allocateTeacherCommand.subjectId())) {
            throw new IllegalArgumentException("Invalid subject id");
        }
        Set<AllocationAggregate> existentAllocations = this.allocationRepository
                .findAllByTeacherIdAndStatusActive(allocateTeacherCommand.teacherId());

        AllocationAggregate allocation = this.allocationMapper.from(allocateTeacherCommand);

        existentAllocations.forEach(ea -> {
            if (isTimeConflicting(ea.getPeriod().start(), ea.getPeriod().end(), allocation.getPeriod().start(),
                    allocation.getPeriod().end())) {
                throw new IllegalArgumentException("Allocation conflicts with allocations that already exists");
            }
        });

        this.allocationRepository.save(allocation);
        this.allocationEventPublisher.publish(new TeacherAllocatedEvent(allocation.getId()));
        return allocation.getId();
    }

    private boolean isTimeConflicting(LocalTime baseStart, LocalTime baseEnd, LocalTime toBeVerifiedStart,
            LocalTime toBeVerifiedEnd) {
        return !(toBeVerifiedEnd.isBefore(baseStart) || toBeVerifiedStart.isAfter(baseEnd));
    }

}
