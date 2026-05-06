package br.nom.corbal.denison.elunari.academic.domain.gateway;

import java.util.UUID;

public interface StudentGateway {
    public boolean existsById(UUID studentId);
}
