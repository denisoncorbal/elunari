package br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.entity.SchoolClassEntity;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.valueobject.SchoolClassName;

public interface SchoolClassRepository {
    public SchoolClassEntity save(SchoolClassEntity schoolClass);

    public boolean existsByName(SchoolClassName name);

    public boolean existsById(UUID schoolClassId);
}
