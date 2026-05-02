package br.nom.corbal.denison.elunari.registration.application.repository;

import br.nom.corbal.denison.elunari.registration.domain.model.entity.TeacherEntity;

public interface TeacherRepository {
    public TeacherEntity save(TeacherEntity teacher);
}
