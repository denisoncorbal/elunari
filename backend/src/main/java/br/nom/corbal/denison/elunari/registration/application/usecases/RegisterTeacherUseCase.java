package br.nom.corbal.denison.elunari.registration.application.usecases;

import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.application.command.RegisterTeacherCommand;
import br.nom.corbal.denison.elunari.registration.application.event.TeacherEventPublisher;
import br.nom.corbal.denison.elunari.registration.application.mapper.TeacherMapper;
import br.nom.corbal.denison.elunari.registration.application.repository.TeacherRepository;
import br.nom.corbal.denison.elunari.registration.domain.event.teacher.TeacherRegisteredEvent;
import br.nom.corbal.denison.elunari.registration.domain.model.entity.TeacherEntity;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class RegisterTeacherUseCase implements BaseUseCase<UUID, RegisterTeacherCommand> {
    private final TeacherRepository teacherRepository;

    private final TeacherEventPublisher<TeacherRegisteredEvent> teacherEventPublisher;

    private final TeacherMapper registerTeacherMapper = new TeacherMapper();

    public RegisterTeacherUseCase(TeacherRepository teacherRepository,
            TeacherEventPublisher<TeacherRegisteredEvent> teacherEventPublisher) {
        this.teacherEventPublisher = teacherEventPublisher;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UUID execute(RegisterTeacherCommand registerTeacherCommand) {
        TeacherEntity teacher = this.registerTeacherMapper.from(registerTeacherCommand);
        this.teacherRepository.save(teacher);
        this.teacherEventPublisher.publish(new TeacherRegisteredEvent(teacher.getId()));
        return teacher.getId();
    }
}
