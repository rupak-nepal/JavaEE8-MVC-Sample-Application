package com.rupak.sample.service.userservice;

import com.rupak.sample.service.domains.UserDomain;
import java.util.List;

/**
 *
 * @author rupak
 */
public interface UserService {

    List<UserDomain> getUserList();

    void addUser(UserDomain userDomain);

    UserDomain getByUserName(String userName);

    void updateUser(UserDomain userDomain);

    void deleteUser(UserDomain userDomain);

    UserDomain authenticateUser(String userName, String password);

    void changeUserPassword(String userName, String currentPassword,
            String newPassword, String confirmPassword);
    
    String resetUserPassword(String username);
    
    void changeContextUserPassword(String currentPassword, String newPassword, String confirmPassword);

    UserDomain getContextUser();

    void updateContextUser(UserDomain userDomain);
}
