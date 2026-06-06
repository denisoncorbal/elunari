package br.nom.corbal.denison.elunari.academic.shared.domain.gateway;

import java.util.UUID;

public interface StudentGateway {
    public boolean existsById(UUID studentId);
}
