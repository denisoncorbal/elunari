package br.nom.corbal.denison.elunari.academic.domain.gateway;

import java.util.UUID;

public interface TeacherGateway {
    public boolean existsById(UUID teacherId);
}
