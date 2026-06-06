package br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.allocation.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.application.command.EnterResponseToInterventionRecordCommand;
import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.application.event.ResponseToInterventionRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.application.mapper.ResponseToInterventionRecordMapper;
import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.event.ResponseToInterventionRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.model.aggregate.ResponseToInterventionRecordAggregate;
import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.repository.ResponseToInterventionRecordRepository;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class EnterResponseToInterventionRecordUseCase
        implements BaseUseCase<UUID, EnterResponseToInterventionRecordCommand> {
    private final TeacherGateway teacherGateway;

    private final StudentGateway studentGateway;

    private final SchoolClassRepository schoolClassRepository;

    private final AllocationRepository allocationRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final ResponseToInterventionRecordRepository responseToInterventionRecordRepository;

    private final ResponseToInterventionRecordEventPublisher<ResponseToInterventionRecordEnteredEvent> responseToInterventionRecordEventPublisher;

    private final ResponseToInterventionRecordMapper responseToInterventionRecordMapper = new ResponseToInterventionRecordMapper();

    public EnterResponseToInterventionRecordUseCase(TeacherGateway teacherGateway, StudentGateway studentGateway,
            SchoolClassRepository schoolClassRepository, AllocationRepository allocationRepository,
            EnrollmentRepository enrollmentRepository,
            ResponseToInterventionRecordRepository responseToInterventionRecordRepository,
            ResponseToInterventionRecordEventPublisher<ResponseToInterventionRecordEnteredEvent> responseToInterventionRecordEventPublisher) {
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
        this.schoolClassRepository = schoolClassRepository;
        this.allocationRepository = allocationRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.responseToInterventionRecordRepository = responseToInterventionRecordRepository;
        this.responseToInterventionRecordEventPublisher = responseToInterventionRecordEventPublisher;
    }

    @Override
    public UUID execute(EnterResponseToInterventionRecordCommand enterResponseToInterventionRecordCommand) {
        if (!this.teacherGateway.existsById(enterResponseToInterventionRecordCommand.teacherId())) {
            throw new IllegalArgumentException("Invalid teacher");
        }
        if (!this.studentGateway.existsById(enterResponseToInterventionRecordCommand.studentId())) {
            throw new IllegalArgumentException("Invalid student");
        }
        if (!this.schoolClassRepository.existsById(enterResponseToInterventionRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Invalid school class");
        }
        if (!this.allocationRepository.isTeacherAllocatedToSchoolClass(
                enterResponseToInterventionRecordCommand.teacherId(),
                enterResponseToInterventionRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Teacher is not allocated to the school class");
        }
        if (!this.enrollmentRepository.isStudentEnrolledToSchoolClass(
                enterResponseToInterventionRecordCommand.studentId(),
                enterResponseToInterventionRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Student is not enrolled to the school class");
        }

        ResponseToInterventionRecordAggregate responseToInterventionRecordAggregate = this.responseToInterventionRecordMapper
                .from(enterResponseToInterventionRecordCommand);

        this.responseToInterventionRecordRepository.save(responseToInterventionRecordAggregate);

        this.responseToInterventionRecordEventPublisher.publish(
                new ResponseToInterventionRecordEnteredEvent(responseToInterventionRecordAggregate.getId()));

        return responseToInterventionRecordAggregate.getId();
    }
}
