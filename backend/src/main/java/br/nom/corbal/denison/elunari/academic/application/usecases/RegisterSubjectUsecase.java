package br.nom.corbal.denison.elunari.academic.application.usecases;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.application.events.subject.SubjectEventPublisher;
import br.nom.corbal.denison.elunari.academic.application.events.subject.SubjectRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.application.repository.SubjectRepository;
import br.nom.corbal.denison.elunari.academic.application.usecases.command.RegisterSubjectCommand;
import br.nom.corbal.denison.elunari.academic.application.usecases.mapper.SubjectMapper;
import br.nom.corbal.denison.elunari.academic.domain.Subject;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUsecase;

public class RegisterSubjectUsecase implements BaseUsecase<UUID, RegisterSubjectCommand> {
    private final SubjectRepository subjectRepository;

    private final SubjectEventPublisher<SubjectRegisteredEvent> subjectEventPublisher;

    private final SubjectMapper registerSubjectMapper = new SubjectMapper();

    public RegisterSubjectUsecase(SubjectRepository subjectRepository,
            SubjectEventPublisher<SubjectRegisteredEvent> subjectEventPublisher) {
        this.subjectEventPublisher = subjectEventPublisher;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public UUID execute(RegisterSubjectCommand registerSubjectCommand) {
        Subject subject = this.registerSubjectMapper.from(registerSubjectCommand);
        this.subjectRepository.save(subject);
        this.subjectEventPublisher.publish(new SubjectRegisteredEvent(subject.getId()));
        return subject.getId();
    }
}
