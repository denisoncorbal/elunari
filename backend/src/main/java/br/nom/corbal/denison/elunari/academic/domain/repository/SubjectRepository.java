package br.nom.corbal.denison.elunari.academic.domain.repository;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.domain.model.entity.SubjectEntity;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SubjectName;

public interface SubjectRepository {
    public SubjectEntity save(SubjectEntity subject);

    public boolean existsByName(SubjectName name);

    public boolean existsById(UUID subjectId);
}
