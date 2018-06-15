package com.rupak.sample.service.entityconverter;

import com.rupak.sample.data.entity.UserEntity;
import com.rupak.sample.platform.common.EntityConverter;
import com.rupak.sample.platform.common.UserType;
import com.rupak.sample.service.domains.UserDomain;
import javax.inject.Named;

/**
 *
 * @author rupak
 */
@Named
public class UserEntityConverter implements EntityConverter<UserEntity, UserDomain> {

    @Override
    public UserEntity toEntity(UserDomain domainObject) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(domainObject.getName());
        userEntity.setPassword(domainObject.getPassword());
        userEntity.setUserId(domainObject.getUserId());
        return userEntity;
    }

    @Override
    public UserDomain toDomainObject(UserEntity entity) {
        UserDomain userDomain = new UserDomain();
        userDomain.setName(entity.getName());
        userDomain.setPassword(entity.getPassword());
        userDomain.setUserId(entity.getUserId());
        return userDomain;
    }

}
