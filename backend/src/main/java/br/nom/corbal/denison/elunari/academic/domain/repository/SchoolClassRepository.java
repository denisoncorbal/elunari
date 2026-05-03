package br.nom.corbal.denison.elunari.academic.domain.repository;

import br.nom.corbal.denison.elunari.academic.domain.model.entity.SchoolClassEntity;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SchoolClassName;

public interface SchoolClassRepository {
    public SchoolClassEntity save(SchoolClassEntity schoolClass);

    public boolean existsByName(SchoolClassName name);
}
