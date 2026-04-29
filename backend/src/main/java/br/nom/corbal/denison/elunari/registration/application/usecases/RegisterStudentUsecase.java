package br.nom.corbal.denison.elunari.registration.application.usecases;

import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.application.events.student.StudentEventPublisher;
import br.nom.corbal.denison.elunari.registration.application.events.student.StudentRegisteredEvent;
import br.nom.corbal.denison.elunari.registration.application.repository.StudentRepository;
import br.nom.corbal.denison.elunari.registration.application.usecases.command.RegisterStudentCommand;
import br.nom.corbal.denison.elunari.registration.application.usecases.mapper.StudentMapper;
import br.nom.corbal.denison.elunari.registration.domain.Student;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUsecase;

public class RegisterStudentUsecase implements BaseUsecase<UUID, RegisterStudentCommand> {
    private final StudentRepository studentRepository;

    private final StudentEventPublisher<StudentRegisteredEvent> studentEventPublisher;

    private final StudentMapper registerStudentMapper = new StudentMapper();

    public RegisterStudentUsecase(StudentRepository studentRepository,
            StudentEventPublisher<StudentRegisteredEvent> studentEventPublisher) {
        this.studentEventPublisher = studentEventPublisher;
        this.studentRepository = studentRepository;
    }

    @Override
    public UUID execute(RegisterStudentCommand registerStudentCommand) {
        Student student = this.registerStudentMapper.from(registerStudentCommand);
        this.studentRepository.save(student);
        this.studentEventPublisher.publish(new StudentRegisteredEvent(student.getId()));
        return student.getId();
    }
}
