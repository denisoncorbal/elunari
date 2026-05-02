package br.nom.corbal.denison.elunari.shared.application.usecase;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCqrs;

public interface BaseUseCase<R, A extends BaseCqrs> {
    public R execute(A commandOrQuery);
}
