package br.nom.corbal.denison.elunari.academic.shared.domain.gateway;

import java.util.UUID;

public interface TeacherGateway {
    public boolean existsById(UUID teacherId);
}
