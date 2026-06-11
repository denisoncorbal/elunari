package br.nom.corbal.denison.elunari.academic.subject.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.subject.application.command.RegisterSubjectCommand;
import br.nom.corbal.denison.elunari.academic.subject.application.event.SubjectEventPublisher;
import br.nom.corbal.denison.elunari.academic.subject.application.mapper.SubjectMapper;
import br.nom.corbal.denison.elunari.academic.subject.domain.event.SubjectRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.subject.domain.model.entity.SubjectEntity;
import br.nom.corbal.denison.elunari.academic.subject.domain.model.valueobject.SubjectName;
import br.nom.corbal.denison.elunari.academic.subject.domain.repository.SubjectRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class RegisterSubjectUseCase implements BaseUseCase<UUID, RegisterSubjectCommand> {
    private final SubjectRepository subjectRepository;

    private final SubjectEventPublisher<SubjectRegisteredEvent> subjectEventPublisher;

    public RegisterSubjectUseCase(SubjectRepository subjectRepository,
            SubjectEventPublisher<SubjectRegisteredEvent> subjectEventPublisher) {
        this.subjectEventPublisher = subjectEventPublisher;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public UUID execute(RegisterSubjectCommand registerSubjectCommand) {
        if (this.subjectRepository.existsByName(new SubjectName(registerSubjectCommand.name()))) {
            throw new IllegalArgumentException("Invalid subject, name must be unique");
        }

        SubjectEntity subject = SubjectMapper.toSubjectEntity(registerSubjectCommand);

        this.subjectRepository.save(subject);
        this.subjectEventPublisher.publish(new SubjectRegisteredEvent(subject.getId()));
        return subject.getId();
    }
}
