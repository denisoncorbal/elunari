package br.nom.corbal.denison.elunari.registration.student.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.student.application.command.RegisterStudentCommand;
import br.nom.corbal.denison.elunari.registration.student.application.event.StudentEventPublisher;
import br.nom.corbal.denison.elunari.registration.student.application.mapper.StudentMapper;
import br.nom.corbal.denison.elunari.registration.student.domain.event.StudentRegisteredEvent;
import br.nom.corbal.denison.elunari.registration.student.domain.model.entity.StudentEntity;
import br.nom.corbal.denison.elunari.registration.student.domain.repository.StudentRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class RegisterStudentUseCase implements BaseUseCase<UUID, RegisterStudentCommand> {
    private final StudentRepository studentRepository;

    private final StudentEventPublisher<StudentRegisteredEvent> studentEventPublisher;

    public RegisterStudentUseCase(StudentRepository studentRepository,
            StudentEventPublisher<StudentRegisteredEvent> studentEventPublisher) {
        this.studentEventPublisher = studentEventPublisher;
        this.studentRepository = studentRepository;
    }

    @Override
    public UUID execute(RegisterStudentCommand registerStudentCommand) {
        StudentEntity student = StudentMapper.toStudentEntity(registerStudentCommand);
        this.studentRepository.save(student);
        this.studentEventPublisher.publish(new StudentRegisteredEvent(student.getId()));
        return student.getId();
    }
}
