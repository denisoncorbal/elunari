package br.nom.corbal.denison.elunari.academic.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.application.command.RegisterAssignementCommand;
import br.nom.corbal.denison.elunari.academic.application.event.assignement.AssignementEventPublisher;
import br.nom.corbal.denison.elunari.academic.application.mapper.AssignementMapper;
import br.nom.corbal.denison.elunari.academic.domain.event.AssignementRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.AssignementAggregate;
import br.nom.corbal.denison.elunari.academic.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.AssignementRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SubjectRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class RegisterAssignementUseCase implements BaseUseCase<UUID, RegisterAssignementCommand> {

    private final TeacherGateway teacherGateway;
    private final StudentGateway studentGateway;
    private final SchoolClassRepository schoolClassRepository;
    private final SubjectRepository subjectRepository;
    private final AllocationRepository allocationRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AssignementRepository assignementRepository;
    private final AssignementEventPublisher<AssignementRegisteredEvent> assignementEventPublisher;

    public RegisterAssignementUseCase(TeacherGateway teacherGateway, StudentGateway studentGateway,
            SchoolClassRepository schoolClassRepository, SubjectRepository subjectRepository,
            AllocationRepository allocationRepository, EnrollmentRepository enrollmentRepository,
            AssignementRepository assignementRepository,
            AssignementEventPublisher<AssignementRegisteredEvent> assignementEventPublisher) {
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
        this.schoolClassRepository = schoolClassRepository;
        this.subjectRepository = subjectRepository;
        this.allocationRepository = allocationRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.assignementRepository = assignementRepository;
        this.assignementEventPublisher = assignementEventPublisher;
    }

    private final AssignementMapper assignementMapper = new AssignementMapper();

    @Override
    public UUID execute(RegisterAssignementCommand registerAssignementCommand) {
        if (!this.teacherGateway.existsById(registerAssignementCommand.teacherId())) {
            throw new IllegalArgumentException("Invalid teacher");
        }

        if (!this.studentGateway.existsById(registerAssignementCommand.studentId())) {
            throw new IllegalArgumentException("Invalid student");
        }

        if (!this.schoolClassRepository.existsById(registerAssignementCommand.schoolClassId())) {
            throw new IllegalArgumentException("Invalid school class");
        }

        if (!this.subjectRepository.existsById(registerAssignementCommand.subjectId())) {
            throw new IllegalArgumentException("Invalid subject");
        }

        if (!this.allocationRepository.isTeacherAllocatedToSchoolClassAndSubject(
                registerAssignementCommand.teacherId(),
                registerAssignementCommand.schoolClassId(),
                registerAssignementCommand.subjectId())) {
            throw new IllegalArgumentException("Teacher is not allocated to the school class and subject");
        }

        if (!this.enrollmentRepository.isStudentEnrolledToSchoolClass(
                registerAssignementCommand.studentId(),
                registerAssignementCommand.schoolClassId())) {
            throw new IllegalArgumentException("Student is not enrolled to the school class");
        }

        AssignementAggregate assignement = this.assignementMapper.from(registerAssignementCommand);

        this.assignementRepository.save(assignement);

        this.assignementEventPublisher.publish(new AssignementRegisteredEvent(assignement.getId()));

        return assignement.getId();

    }
}
