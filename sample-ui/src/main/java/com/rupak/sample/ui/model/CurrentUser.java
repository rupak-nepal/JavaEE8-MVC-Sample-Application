package com.rupak.sample.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author rupak
 */
public class CurrentUser implements Serializable {

    private String fullName;
    private String userName;

    public CurrentUser(String fullName, String userName) {
        this.fullName = fullName;
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
