package com.rupak.sample.ui.controller;

import com.rupak.sample.service.domains.UserDomain;
import com.rupak.sample.service.userservice.UserService;
import com.rupak.sample.ui.converter.UserModelConverter;
import com.rupak.sample.ui.interceptors.ServiceInvoker;
import com.rupak.sample.ui.model.CurrentUser;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.CsrfValid;
import javax.mvc.annotation.View;
import javax.mvc.binding.BindingResult;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import com.rupak.sample.ui.model.Error;
import com.rupak.sample.ui.model.Message;
import com.rupak.sample.ui.model.UserModel;
import com.rupak.sample.ui.utils.ControllerUtils;
import com.rupak.sample.ui.utils.WebSecurityUtils;

/**
 *
 * @author rupak
 */
@Controller
@Path("profile")
@Named
@RequestScoped
@ServiceInvoker
public class ProfileController {

    private static final Logger LOG = Logger.getLogger(ProfileController.class.getName());
    
    private Message message;
    private Models profileModels;
    private BindingResult bindingResult;
    private Error error;
    private UserService userService;
    private UserModelConverter userModelConverter;
    private WebSecurityUtils webSecurityUtils;

    public ProfileController() {
    }

    @Inject
    public ProfileController(Message message, Models profileModels, BindingResult bindingResult, Error error, UserService userService, UserModelConverter userModelConverter, WebSecurityUtils webSecurityUtils) {
        this.message = message;
        this.profileModels = profileModels;
        this.bindingResult = bindingResult;
        this.error = error;
        this.userService = userService;
        this.userModelConverter = userModelConverter;
        this.webSecurityUtils = webSecurityUtils;
    }
    
    @GET
    @View(value = "profile/profile.jsp")
    public void getProfile(){
        LOG.info("this is too much");
        
        LOG.info("inside profile controller :;;"+this.userService.getContextUser().getUserId());
        UserDomain userDomain = this.userService.getContextUser();
        this.profileModels.put("user", userDomain);
    }
    
    @POST
    @CsrfValid
    @ServiceInvoker(view = "profile/profile.jsp",model="user")
    public Response updateProfile(@BeanParam UserModel user){
        LOG.info("updated user profile "+user.toString());
        UserDomain userDomain = this.userModelConverter.toDomainObject(user);
        userDomain.setUserId(this.webSecurityUtils.getCurrentUser().getUserName());
        this.userService.updateContextUser(userDomain);
        this.message.addInfoMessage("Profile changed successfully.");
        this.webSecurityUtils.updateCurrentUser(
                new CurrentUser(
                        userDomain.getName(),
                        userDomain.getUserId()));
        return Response.ok(ControllerUtils.REDIRECT_TO + "profile").build();
    }
    
    @POST
    @Path("change-password")
    @CsrfValid
    @ServiceInvoker(view = "profile/profile.jsp", model = "user")
    public Response updatePassword(@BeanParam UserModel user) {
        LOG.info("user detail new :"+user.toString());
        this.userService.changeContextUserPassword(user.getCurrentPass(), user.getPassword(), user.getConfirmPassword());
        this.message.addInfoMessage("Password changed successfully ");
        return Response.ok(ControllerUtils.REDIRECT_TO + "logout").build();
    }
    
    @POST
    @Path("reset-password")
    @CsrfValid
    @ServiceInvoker(view = "profile/profile.jsp", model = "user")
    public Response resetPassword() {
        LOG.info("reset user password "+this.webSecurityUtils.getCurrentUser().getUserName());
        String resetPassword = this.userService.resetUserPassword(this.webSecurityUtils.getCurrentUser().getUserName());
        this.message.addInfoMessage("Password reset successfully :- "+resetPassword);
        return Response.ok(ControllerUtils.REDIRECT_TO + "profile").build();
    }
}
