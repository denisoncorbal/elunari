package br.nom.corbal.denison.elunari.academic.teachernoterecord.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.allocation.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.application.command.EnterTeacherNoteRecordCommand;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.application.event.TeacherNoteRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.application.mapper.TeacherNoteRecordMapper;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.event.TeacherNoteRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.aggregate.TeacherNoteRecordAggregate;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.repository.TeacherNoteRecordRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class EnterTeacherNoteRecordUseCase implements BaseUseCase<UUID, EnterTeacherNoteRecordCommand> {

    private final TeacherGateway teacherGateway;

    private final StudentGateway studentGateway;

    private final SchoolClassRepository schoolClassRepository;

    private final AllocationRepository allocationRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final TeacherNoteRecordRepository teacherNoteRecordRespository;

    private final TeacherNoteRecordEventPublisher<TeacherNoteRecordEnteredEvent> teacherNoteRecordEventPublisher;

    public EnterTeacherNoteRecordUseCase(TeacherGateway teacherGateway, StudentGateway studentGateway,
            SchoolClassRepository schoolClassRepository, AllocationRepository allocationRepository,
            EnrollmentRepository enrollmentRepository,
            TeacherNoteRecordRepository teacherNoteRecordRepository,
            TeacherNoteRecordEventPublisher<TeacherNoteRecordEnteredEvent> teacherNoteRecordEventPublisher) {
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
        this.schoolClassRepository = schoolClassRepository;
        this.allocationRepository = allocationRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.teacherNoteRecordRespository = teacherNoteRecordRepository;
        this.teacherNoteRecordEventPublisher = teacherNoteRecordEventPublisher;
    }

    @Override
    public UUID execute(EnterTeacherNoteRecordCommand enterTeacherNoteRecordCommand) {
        if (!this.teacherGateway.existsById(enterTeacherNoteRecordCommand.teacherId())) {
            throw new IllegalArgumentException("Invalid teacher");
        }
        if (!this.studentGateway.existsById(enterTeacherNoteRecordCommand.studentId())) {
            throw new IllegalArgumentException("Invalid student");
        }
        if (!this.schoolClassRepository.existsById(enterTeacherNoteRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Invalid school class");
        }
        if (!this.allocationRepository.isTeacherAllocatedToSchoolClass(
                enterTeacherNoteRecordCommand.teacherId(),
                enterTeacherNoteRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Teacher is not allocated to the school class");
        }
        if (!this.enrollmentRepository.isStudentEnrolledToSchoolClass(
                enterTeacherNoteRecordCommand.studentId(),
                enterTeacherNoteRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Student is not enrolled to the school class");
        }

        TeacherNoteRecordAggregate teacherNoteRecordAggregate = TeacherNoteRecordMapper
                .toTeacherNoteRecordAggregate(enterTeacherNoteRecordCommand);

        this.teacherNoteRecordRespository.save(teacherNoteRecordAggregate);

        this.teacherNoteRecordEventPublisher.publish(
                new TeacherNoteRecordEnteredEvent(teacherNoteRecordAggregate.getId()));

        return teacherNoteRecordAggregate.getId();
    }

}
