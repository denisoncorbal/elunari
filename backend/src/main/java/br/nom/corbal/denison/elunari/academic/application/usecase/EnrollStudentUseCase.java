package br.nom.corbal.denison.elunari.academic.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.application.command.EnrollStudentCommand;
import br.nom.corbal.denison.elunari.academic.application.event.enrollment.EnrollmentEventPublisher;
import br.nom.corbal.denison.elunari.academic.application.mapper.EnrollmentMapper;
import br.nom.corbal.denison.elunari.academic.domain.event.StudentEnrolledEvent;
import br.nom.corbal.denison.elunari.academic.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.EnrollmentAggregate;
import br.nom.corbal.denison.elunari.academic.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class EnrollStudentUseCase implements BaseUseCase<UUID, EnrollStudentCommand> {

    private final EnrollmentMapper enrollmentMapper = new EnrollmentMapper();

    private final StudentGateway studentGateway;
    private final SchoolClassRepository schoolClassRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentEventPublisher<StudentEnrolledEvent> enrollmentEventPublisher;

    public EnrollStudentUseCase(StudentGateway studentGateway, SchoolClassRepository schoolClassRepository,
            EnrollmentRepository enrollmentRepository,
            EnrollmentEventPublisher<StudentEnrolledEvent> enrollmentEventPublisher) {
        this.studentGateway = studentGateway;
        this.schoolClassRepository = schoolClassRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentEventPublisher = enrollmentEventPublisher;
    }

    @Override
    public UUID execute(EnrollStudentCommand enrollStudentCommand) {
        if (!this.studentGateway.existsById(enrollStudentCommand.studentId())) {
            throw new IllegalArgumentException("Invalid student id");
        }
        ;
        if (!this.schoolClassRepository.existsById(enrollStudentCommand.schoolClassId())) {
            throw new IllegalArgumentException("Invalid school class id");
        }
        ;
        if (this.enrollmentRepository.isStudentAlreadyEnrolled(enrollStudentCommand.studentId())) {
            throw new IllegalArgumentException("Student already enrolled to a school class");
        }

        EnrollmentAggregate enrollmentAggregate = this.enrollmentMapper.from(enrollStudentCommand);

        this.enrollmentRepository.save(enrollmentAggregate);

        this.enrollmentEventPublisher.publish(new StudentEnrolledEvent(enrollmentAggregate.getId()));

        return enrollmentAggregate.getId();
    }

}
