package br.nom.corbal.denison.elunari.academic.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.application.command.RegisterSubjectCommand;
import br.nom.corbal.denison.elunari.academic.application.event.subject.SubjectEventPublisher;
import br.nom.corbal.denison.elunari.academic.application.mapper.SubjectMapper;
import br.nom.corbal.denison.elunari.academic.application.repository.SubjectRepository;
import br.nom.corbal.denison.elunari.academic.domain.event.SubjectRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.model.entity.SubjectEntity;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class RegisterSubjectUseCase implements BaseUseCase<UUID, RegisterSubjectCommand> {
    private final SubjectRepository subjectRepository;

    private final SubjectEventPublisher<SubjectRegisteredEvent> subjectEventPublisher;

    private final SubjectMapper registerSubjectMapper = new SubjectMapper();

    public RegisterSubjectUseCase(SubjectRepository subjectRepository,
            SubjectEventPublisher<SubjectRegisteredEvent> subjectEventPublisher) {
        this.subjectEventPublisher = subjectEventPublisher;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public UUID execute(RegisterSubjectCommand registerSubjectCommand) {
        SubjectEntity subject = this.registerSubjectMapper.from(registerSubjectCommand);
        this.subjectRepository.save(subject);
        this.subjectEventPublisher.publish(new SubjectRegisteredEvent(subject.getId()));
        return subject.getId();
    }
}
