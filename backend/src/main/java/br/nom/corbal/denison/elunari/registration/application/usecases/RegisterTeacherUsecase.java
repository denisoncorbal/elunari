package br.nom.corbal.denison.elunari.registration.application.usecases;

import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.application.events.teacher.TeacherEventPublisher;
import br.nom.corbal.denison.elunari.registration.application.events.teacher.TeacherRegisteredEvent;
import br.nom.corbal.denison.elunari.registration.application.repository.TeacherRepository;
import br.nom.corbal.denison.elunari.registration.application.usecases.command.RegisterTeacherCommand;
import br.nom.corbal.denison.elunari.registration.application.usecases.mapper.TeacherMapper;
import br.nom.corbal.denison.elunari.registration.domain.Teacher;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUsecase;

public class RegisterTeacherUsecase implements BaseUsecase<UUID, RegisterTeacherCommand> {
    private final TeacherRepository teacherRepository;

    private final TeacherEventPublisher<TeacherRegisteredEvent> teacherEventPublisher;

    private final TeacherMapper registerTeacherMapper = new TeacherMapper();

    public RegisterTeacherUsecase(TeacherRepository teacherRepository,
            TeacherEventPublisher<TeacherRegisteredEvent> teacherEventPublisher) {
        this.teacherEventPublisher = teacherEventPublisher;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UUID execute(RegisterTeacherCommand registerTeacherCommand) {
        Teacher teacher = this.registerTeacherMapper.from(registerTeacherCommand);
        this.teacherRepository.save(teacher);
        this.teacherEventPublisher.publish(new TeacherRegisteredEvent(teacher.getId()));
        return teacher.getId();
    }
}
