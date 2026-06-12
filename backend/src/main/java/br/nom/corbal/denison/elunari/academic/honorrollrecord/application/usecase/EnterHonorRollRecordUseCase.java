package br.nom.corbal.denison.elunari.academic.honorrollrecord.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.allocation.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.application.command.EnterHonorRollRecordCommand;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.application.event.HonorRollRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.application.mapper.HonorRollRecordMapper;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.event.HonorRollRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.model.aggregate.HonorRollRecordAggregate;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.repository.HonorRollRecordRepository;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class EnterHonorRollRecordUseCase implements BaseUseCase<UUID, EnterHonorRollRecordCommand> {
    private final TeacherGateway teacherGateway;

    private final StudentGateway studentGateway;

    private final SchoolClassRepository schoolClassRepository;

    private final AllocationRepository allocationRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final HonorRollRecordRepository honorRollRecordRepository;

    private final HonorRollRecordEventPublisher<HonorRollRecordEnteredEvent> honorRollRecordEventPublisher;

    public EnterHonorRollRecordUseCase(TeacherGateway teacherGateway, StudentGateway studentGateway,
            SchoolClassRepository schoolClassRepository, AllocationRepository allocationRepository,
            EnrollmentRepository enrollmentRepository,
            HonorRollRecordRepository honorRollRecordRepository,
            HonorRollRecordEventPublisher<HonorRollRecordEnteredEvent> honorRollRecordEventPublisher) {
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
        this.schoolClassRepository = schoolClassRepository;
        this.allocationRepository = allocationRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.honorRollRecordRepository = honorRollRecordRepository;
        this.honorRollRecordEventPublisher = honorRollRecordEventPublisher;
    }

    @Override
    public UUID execute(EnterHonorRollRecordCommand enterHonorRollRecordCommand) {
        if (!this.teacherGateway.existsById(enterHonorRollRecordCommand.teacherId())) {
            throw new IllegalArgumentException("Invalid teacher");
        }
        if (!this.studentGateway.existsById(enterHonorRollRecordCommand.studentId())) {
            throw new IllegalArgumentException("Invalid student");
        }
        if (!this.schoolClassRepository.existsById(enterHonorRollRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Invalid school class");
        }
        if (!this.allocationRepository.isTeacherAllocatedToSchoolClass(
                enterHonorRollRecordCommand.teacherId(),
                enterHonorRollRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Teacher is not allocated to the school class");
        }
        if (!this.enrollmentRepository.isStudentEnrolledToSchoolClass(
                enterHonorRollRecordCommand.studentId(),
                enterHonorRollRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Student is not enrolled to the school class");
        }

        HonorRollRecordAggregate honorRollRecordAggregate = HonorRollRecordMapper
                .toHonorRollRecordAggregate(enterHonorRollRecordCommand);

        this.honorRollRecordRepository.save(honorRollRecordAggregate);

        this.honorRollRecordEventPublisher.publish(
                new HonorRollRecordEnteredEvent(honorRollRecordAggregate.getId()));

        return honorRollRecordAggregate.getId();
    }
}
