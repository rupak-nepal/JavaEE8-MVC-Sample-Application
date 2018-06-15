package com.rupak.sample.platform.common;

/**
 *
 * @author rupak
 */
public interface EntityConverter<E extends Entity, DO extends DomainObject> {

    E toEntity(DO domainObject);

    DO toDomainObject(E entity);
}
