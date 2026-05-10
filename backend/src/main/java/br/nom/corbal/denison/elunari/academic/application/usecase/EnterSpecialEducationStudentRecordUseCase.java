package br.nom.corbal.denison.elunari.academic.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.application.command.EnterSpecialEducationStudentRecordCommand;
import br.nom.corbal.denison.elunari.academic.application.event.specialeducationstudentrecord.SpecialEducationStudentRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.application.mapper.SpecialEducationStudentRecordMapper;
import br.nom.corbal.denison.elunari.academic.domain.event.SpecialEducationStudentRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.SpecialEducationStudentRecordAggregate;
import br.nom.corbal.denison.elunari.academic.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SpecialEducationStudentRecordRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class EnterSpecialEducationStudentRecordUseCase
        implements BaseUseCase<UUID, EnterSpecialEducationStudentRecordCommand> {
    private final TeacherGateway teacherGateway;

    private final StudentGateway studentGateway;

    private final SchoolClassRepository schoolClassRepository;

    private final AllocationRepository allocationRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final SpecialEducationStudentRecordRepository specialEducationStudentRecordRespository;

    private final SpecialEducationStudentRecordEventPublisher<SpecialEducationStudentRecordEnteredEvent> specialEducationStudentRecordEventPublisher;

    private final SpecialEducationStudentRecordMapper specialEducationStudentRecordMapper = new SpecialEducationStudentRecordMapper();

    public EnterSpecialEducationStudentRecordUseCase(TeacherGateway teacherGateway, StudentGateway studentGateway,
            SchoolClassRepository schoolClassRepository, AllocationRepository allocationRepository,
            EnrollmentRepository enrollmentRepository,
            SpecialEducationStudentRecordRepository SpecialEducationStudentRecordRepository,
            SpecialEducationStudentRecordEventPublisher<SpecialEducationStudentRecordEnteredEvent> specialEducationStudentRecordEventPublisher) {
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
        this.schoolClassRepository = schoolClassRepository;
        this.allocationRepository = allocationRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.specialEducationStudentRecordRespository = SpecialEducationStudentRecordRepository;
        this.specialEducationStudentRecordEventPublisher = specialEducationStudentRecordEventPublisher;
    }

    @Override
    public UUID execute(EnterSpecialEducationStudentRecordCommand enterSpecialEducationStudentRecordCommand) {
        if (!this.teacherGateway.existsById(enterSpecialEducationStudentRecordCommand.teacherId())) {
            throw new IllegalArgumentException("Invalid teacher");
        }
        if (!this.studentGateway.existsById(enterSpecialEducationStudentRecordCommand.studentId())) {
            throw new IllegalArgumentException("Invalid student");
        }
        if (!this.schoolClassRepository.existsById(enterSpecialEducationStudentRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Invalid school class");
        }
        if (!this.allocationRepository.isTeacherAllocatedToSchoolClass(
                enterSpecialEducationStudentRecordCommand.teacherId(),
                enterSpecialEducationStudentRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Teacher is not allocated to the school class");
        }
        if (!this.enrollmentRepository.isStudentEnrolledToSchoolClass(
                enterSpecialEducationStudentRecordCommand.studentId(),
                enterSpecialEducationStudentRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Student is not enrolled to the school class");
        }

        SpecialEducationStudentRecordAggregate specialEducationStudentRecordAggregate = this.specialEducationStudentRecordMapper
                .from(enterSpecialEducationStudentRecordCommand);

        this.specialEducationStudentRecordRespository.save(specialEducationStudentRecordAggregate);

        this.specialEducationStudentRecordEventPublisher.publish(
                new SpecialEducationStudentRecordEnteredEvent(specialEducationStudentRecordAggregate.getId()));

        return specialEducationStudentRecordAggregate.getId();
    }
}
