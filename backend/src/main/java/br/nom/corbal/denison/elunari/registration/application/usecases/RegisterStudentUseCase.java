package br.nom.corbal.denison.elunari.registration.application.usecases;

import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.application.command.RegisterStudentCommand;
import br.nom.corbal.denison.elunari.registration.application.event.StudentEventPublisher;
import br.nom.corbal.denison.elunari.registration.application.mapper.StudentMapper;
import br.nom.corbal.denison.elunari.registration.domain.event.student.StudentRegisteredEvent;
import br.nom.corbal.denison.elunari.registration.domain.model.entity.StudentEntity;
import br.nom.corbal.denison.elunari.registration.domain.repository.StudentRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class RegisterStudentUseCase implements BaseUseCase<UUID, RegisterStudentCommand> {
    private final StudentRepository studentRepository;

    private final StudentEventPublisher<StudentRegisteredEvent> studentEventPublisher;

    private final StudentMapper registerStudentMapper = new StudentMapper();

    public RegisterStudentUseCase(StudentRepository studentRepository,
            StudentEventPublisher<StudentRegisteredEvent> studentEventPublisher) {
        this.studentEventPublisher = studentEventPublisher;
        this.studentRepository = studentRepository;
    }

    @Override
    public UUID execute(RegisterStudentCommand registerStudentCommand) {
        StudentEntity student = this.registerStudentMapper.from(registerStudentCommand);
        this.studentRepository.save(student);
        this.studentEventPublisher.publish(new StudentRegisteredEvent(student.getId()));
        return student.getId();
    }
}
