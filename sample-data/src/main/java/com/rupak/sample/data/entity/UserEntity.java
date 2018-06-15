package com.rupak.sample.data.entity;

import com.rupak.sample.platform.common.Entity;
import java.util.Objects;

/**
 *
 * @author rupak
 */
public class UserEntity extends Entity{
    private String name;
    private String userId;
    private transient String password;

    public UserEntity() {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.userId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserEntity other = (UserEntity) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "name=" + name + ", userId=" + userId + ", password=" + password + '}';
    }

}
