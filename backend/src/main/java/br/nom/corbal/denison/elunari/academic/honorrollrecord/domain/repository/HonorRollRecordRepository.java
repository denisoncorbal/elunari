package br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.repository;

import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.model.aggregate.HonorRollRecordAggregate;

public interface HonorRollRecordRepository {
    public HonorRollRecordAggregate save(HonorRollRecordAggregate honorRollRecordAggregate);

}
