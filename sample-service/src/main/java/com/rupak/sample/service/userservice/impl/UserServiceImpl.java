package com.rupak.sample.service.userservice.impl;

import com.rupak.sample.data.dao.UserDao;
import com.rupak.sample.data.entity.UserEntity;
import com.rupak.sample.platform.common.BeanUtils;
import com.rupak.sample.platform.common.SampleApplicationConstants;
import com.rupak.sample.platform.common.security.SecurityUtils;
import com.rupak.sample.platform.configs.SampleApplicationContextHolder;
import com.rupak.sample.platform.exceptions.SampleException;
import com.rupak.sample.service.domains.UserDomain;
import com.rupak.sample.service.entityconverter.UserEntityConverter;
import com.rupak.sample.service.userservice.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rupak
 */
@Named
@Dependent
public class UserServiceImpl implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());
    private UserDao userDao;
    private UserEntityConverter userEntityConverter;

    @Inject
    public UserServiceImpl(UserDao userDao, UserEntityConverter userEntityConverter) {
        this.userDao = userDao;
        this.userEntityConverter = userEntityConverter;
    }

    @Override
    public List<UserDomain> getUserList() {
        List<UserDomain> list = new ArrayList();
        List<UserEntity> entityList = this.userDao.findAll();
        LOG.info("entity list size :" + entityList.size());
        entityList.forEach(t -> {
            list.add(this.userEntityConverter.toDomainObject(t));
        });
        return list;
    }

    @Override
    public void addUser(UserDomain userDomain) {
        BeanUtils.trimBean(userDomain);
        LOG.info("User Domain received at service :" + userDomain.toString());
        //DAO save the add
        if (!StringUtils.equals(userDomain.getPassword(), userDomain.getConfirmPassword())) {
            throw new SampleException(SampleException.ExceptionType.USER_CREDENTIAL_NOT_MATCH);
        }
        UserEntity userEntity = this.userDao.findByKey(userDomain.getUserId());
        if (userEntity != null) {
            throw new SampleException(SampleException.ExceptionType.USER_ALREADY_EXISTS);
        }
        userDomain.setPassword(SecurityUtils.encodeString(userDomain.getPassword()));
        this.userDao.addUser(this.userEntityConverter.toEntity(userDomain));
    }

    @Override
    public UserDomain getByUserName(String userName) {
        UserEntity userEntity = this.userDao.findByKey(userName);
        if (userEntity != null) {
            return this.userEntityConverter.toDomainObject(userEntity);
        }
        return null;
    }

    @Override
    public void updateUser(UserDomain userDomain) {
        BeanUtils.trimBean(userDomain);
        LOG.info("entity to be updated. :" + userDomain.toString());
        UserEntity userEntity = this.userDao.findByKey(userDomain.getUserId());
        if (userEntity == null) {
            throw new SampleException(SampleException.ExceptionType.USER_NOT_FOUND);
        }
        LOG.info("entity to be updated. :" + userEntity.toString());
        this.userDao.updateUser(this.userEntityConverter.toEntity(userDomain));
    }


    @Override
    public UserDomain authenticateUser(String userName, String password) {
        UserDomain userDomain = this.userEntityConverter.toDomainObject(
                this.doUserAuthentication(userName, password));
        return userDomain;
    }

    private UserEntity doUserAuthentication(String username, String password) {
        UserEntity userEntity = this.userDao.findByKey(username);
        if (userEntity == null) {
            throw new SampleException(SampleException.ExceptionType.USER_NOT_FOUND);
        }
        if (!SecurityUtils.isEqualEncoding(userEntity.getPassword(), password)) {
            throw new SampleException(SampleException.ExceptionType.USER_CREDENTIAL_NOT_MATCH);
        }
        return userEntity;
    }

    @Override
    public void deleteUser(UserDomain userDomain) {
        this.userDao.deleteUser(this.userEntityConverter.toEntity(userDomain));
    }

    @Override
    public void changeUserPassword(String userName, String currentPassword, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new SampleException(SampleException.ExceptionType.USER_NEW_PASSWORD_DOES_NOT_MATCHED);
        }
        if (StringUtils.isBlank(newPassword)) {
            throw new SampleException(SampleException.ExceptionType.USER_NEW_PASSWORD_DOES_NOT_MATCHED);
        }
        UserEntity userEntity = this.doUserAuthentication(userName, currentPassword);
        userEntity.setPassword(SecurityUtils.encodeString(newPassword));
        this.userDao.updateUser(userEntity);
    }

    @Override
    public String resetUserPassword(String username) {
        String defaultPassWord = SampleApplicationConstants.DEFAULT_PASSWORD;
        UserEntity userEntity = this.userDao.findByKey(username);
        if (userEntity == null) {
            throw new SampleException(SampleException.ExceptionType.USER_NOT_FOUND);
        }
        userEntity.setPassword(SecurityUtils.encodeString(defaultPassWord));
        this.userDao.changeUserPassword(userEntity);
        return defaultPassWord;
    }

    @Override
    public void changeContextUserPassword(String currentPassword, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new SampleException(SampleException.ExceptionType.USER_NEW_PASSWORD_DOES_NOT_MATCHED);
        }
        if (StringUtils.equals(currentPassword, newPassword)) {
            throw new SampleException(SampleException.ExceptionType.USER_NEW_PASSWORD_MATCHED_OLD_PASSWORD);
        }
        UserEntity userEntity = this.doUserAuthentication(
                SampleApplicationContextHolder.getContext().getCurrentUser(), currentPassword);
        userEntity.setPassword(SecurityUtils.encodeString(newPassword));
        this.userDao.changeUserPassword(userEntity);
    }

    @Override
    public UserDomain getContextUser() {
        LOG.info("check : contextUser "+SampleApplicationContextHolder.getContext().getCurrentUser());
        UserEntity userEntity = this.userDao.findByKey(SampleApplicationContextHolder.getContext().getCurrentUser());
        if (userEntity != null) {
            return this.userEntityConverter.toDomainObject(userEntity);
        }
        return null;
    }

    @Override
    public void updateContextUser(UserDomain userDomain) {
        BeanUtils.trimBean(userDomain);
        UserEntity usrEntity = this.userDao
                .findByKey(SampleApplicationContextHolder.getContext().getCurrentUser());
        usrEntity.setName(userDomain.getName());
        this.userDao.updateUser(usrEntity);
    }
}
