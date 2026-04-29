package br.nom.corbal.denison.elunari.registration.application.usecases;

import br.nom.corbal.denison.elunari.registration.application.events.StudentEventPublisher;
import br.nom.corbal.denison.elunari.registration.application.events.StudentRegisteredEvent;
import br.nom.corbal.denison.elunari.registration.application.repository.StudentRepository;
import br.nom.corbal.denison.elunari.registration.application.usecases.command.RegisterStudentCommand;
import br.nom.corbal.denison.elunari.registration.domain.Student;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUsecase;

public class RegisterStudentUsecase implements BaseUsecase<Void, RegisterStudentCommand> {
    private final StudentRepository studentRepository;

    private final StudentEventPublisher<StudentRegisteredEvent> studentEventPublisher;

    private final RegisterStudentMapper registerStudentMapper = new RegisterStudentMapper();

    public RegisterStudentUsecase(StudentRepository studentRepository,
            StudentEventPublisher<StudentRegisteredEvent> studentEventPublisher) {
        this.studentEventPublisher = studentEventPublisher;
        this.studentRepository = studentRepository;
    }

    @Override
    public Void execute(RegisterStudentCommand registerStudentCommand) {
        Student student = this.registerStudentMapper.from(registerStudentCommand);
        this.studentRepository.save(student);
        this.studentEventPublisher.publish(new StudentRegisteredEvent(student.getId()));
        return null;
    }
}
