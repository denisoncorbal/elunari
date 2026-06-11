package br.nom.corbal.denison.elunari.registration.teacher.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.teacher.application.command.RegisterTeacherCommand;
import br.nom.corbal.denison.elunari.registration.teacher.application.event.TeacherEventPublisher;
import br.nom.corbal.denison.elunari.registration.teacher.application.mapper.TeacherMapper;
import br.nom.corbal.denison.elunari.registration.teacher.domain.event.TeacherRegisteredEvent;
import br.nom.corbal.denison.elunari.registration.teacher.domain.model.entity.TeacherEntity;
import br.nom.corbal.denison.elunari.registration.teacher.domain.repository.TeacherRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class RegisterTeacherUseCase implements BaseUseCase<UUID, RegisterTeacherCommand> {
    private final TeacherRepository teacherRepository;

    private final TeacherEventPublisher<TeacherRegisteredEvent> teacherEventPublisher;

    public RegisterTeacherUseCase(TeacherRepository teacherRepository,
            TeacherEventPublisher<TeacherRegisteredEvent> teacherEventPublisher) {
        this.teacherEventPublisher = teacherEventPublisher;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UUID execute(RegisterTeacherCommand registerTeacherCommand) {
        TeacherEntity teacher = TeacherMapper.toTeacherEntity(registerTeacherCommand);
        this.teacherRepository.save(teacher);
        this.teacherEventPublisher.publish(new TeacherRegisteredEvent(teacher.getId()));
        return teacher.getId();
    }
}
