package br.nom.corbal.denison.elunari.academic.domain.repository;

import br.nom.corbal.denison.elunari.academic.domain.model.entity.SubjectEntity;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SubjectName;

public interface SubjectRepository {
    public SubjectEntity save(SubjectEntity subject);

    public boolean existsByName(SubjectName name);
}
