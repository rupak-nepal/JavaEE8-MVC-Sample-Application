package com.rupak.sample.data.dao;

import com.rupak.sample.data.entity.UserEntity;
import java.util.List;
import org.mybatis.cdi.Mapper;

/**
 *
 * @author rupak
 */
@Mapper
public interface UserDao {

    void addUser(UserEntity userEntity);

    List<UserEntity> findAll();

    UserEntity findByKey(String userId);

    public void updateUser(UserEntity entity);

    public void deleteUser(UserEntity entity);

    public void changeUserPassword(UserEntity entity);
}
