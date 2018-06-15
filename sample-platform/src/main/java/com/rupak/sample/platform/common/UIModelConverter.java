package com.rupak.sample.platform.common;

import java.util.List;

/**
 *
 * @author rupak
 */
public interface UIModelConverter<M extends UIModel, D extends DomainObject> {

    M toUIModel(D domainObject);

    D toDomainObject(M uiModel);

    List<M> toUIModelList(List<D> domainObjectList);
}
