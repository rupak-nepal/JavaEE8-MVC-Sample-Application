package com.rupak.sample.service.domains;

import com.rupak.sample.platform.common.DomainObject;
import com.rupak.sample.platform.common.UserType;
import java.io.Serializable;
/**
 *
 * @author rupak
 */
public class UserDomain extends DomainObject implements Serializable{
    private String name;
    private String userId;
    private String password;
    private String confirmPassword;
    public UserDomain() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserDomain{" + "name=" + name + ", userId=" + userId + ", password=" + password + ", confirmPassword=" + confirmPassword + '}';
    }

}
