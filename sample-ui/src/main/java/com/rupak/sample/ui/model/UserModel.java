package com.rupak.sample.ui.model;

import com.rupak.sample.platform.common.UIModel;
import java.io.Serializable;
import javax.mvc.binding.MvcBinding;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.FormParam;

/**
 *
 * @author rupak
 */
public class UserModel implements UIModel,Serializable{

    @MvcBinding
    @FormParam("name")
    @NotEmpty(message = "Full Name is empty")
    private String name;
    
    @FormParam("userId")
    @NotEmpty(message = "UserId is empty")
    private String userId;
    
    @FormParam("password")
    @NotEmpty(message = "Password is empty")
    private String password;
    
    @FormParam("confirmPassword")
    @NotEmpty(message = "Confirm Password is empty")
    private String confirmPassword;
    
    @FormParam("currentPass")
    @NotEmpty(message = "Current Password is empty")
    private String currentPass;
    

    public UserModel(String userId) {
        this.userId = userId;
    }

    public UserModel() {
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

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    @Override
    public String toString() {
        return "UserModel{" + "name=" + name + ", userId=" + userId + ", password=" + password + ", confirmPassword=" + confirmPassword + ", currentPass=" + currentPass + '}';
    }

}
