package br.nom.corbal.denison.elunari.academic.subject.domain.repository;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.subject.domain.model.entity.SubjectEntity;
import br.nom.corbal.denison.elunari.academic.subject.domain.model.valueobject.SubjectName;

public interface SubjectRepository {
    public SubjectEntity save(SubjectEntity subject);

    public boolean existsByName(SubjectName name);

    public boolean existsById(UUID subjectId);
}
