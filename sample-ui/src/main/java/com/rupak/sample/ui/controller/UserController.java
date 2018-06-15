package com.rupak.sample.ui.controller;

import com.rupak.sample.platform.exceptions.SampleException;
import com.rupak.sample.service.domains.UserDomain;
import com.rupak.sample.service.userservice.UserService;
import com.rupak.sample.ui.converter.UserModelConverter;
import com.rupak.sample.ui.interceptors.ServiceInvoker;
import com.rupak.sample.ui.model.Message;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.mvc.binding.BindingResult;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.rupak.sample.ui.model.Error;
import com.rupak.sample.ui.model.UserModel;
import com.rupak.sample.ui.utils.ControllerUtils;
import com.rupak.sample.ui.utils.WebSecurityUtils;
import javax.inject.Inject;
import javax.mvc.annotation.CsrfValid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Sameer
 */
@Controller
@Path("users")
@Named
@RequestScoped
@ServiceInvoker
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class.getName());

    private Message message;
    private Models userModel;
    private BindingResult bindingResult;
    private Error error;
    private UserService userService;
    private UserModelConverter userModelConverter;
    private WebSecurityUtils webSecurityUtils;

    public UserController() {
    }

    @Inject
    public UserController(Message message, Models userModel, BindingResult bindingResult,
            Error error, UserService userService, UserModelConverter userModelConverter,
            WebSecurityUtils webSecurityUtils) {
        this.message = message;
        this.userModel = userModel;
        this.bindingResult = bindingResult;
        this.error = error;
        this.userService = userService;
        this.userModelConverter = userModelConverter;
        this.webSecurityUtils = webSecurityUtils;
    }

    @GET
    @View("users/list.jsp")
    public void getUserList() {
        this.userModel.put("users",
                this.userModelConverter.toUIModelList(this.userService.getUserList()));
    }

    @GET
    @View("users/add.jsp")
    @Path("new")
    public void addNewUserPage() {
    }

    @GET
    @View("users/view.jsp")
    @Path("{userid}")
    public void showUser(@PathParam("userid") String userId) {
        this.logger.info(("User ID :: " + userId));
        UserDomain userDomain = this.userService.getByUserName(userId);
        if (userDomain == null) {
            message.addErrorMessage("User not found.");
        } else {
            this.userModel.put("user",
                    this.userModelConverter.toUIModel(this.userService.getByUserName(userId)));
        }
    }

    @GET
    @View("users/list.jsp")
    @Path("{userid}/delete")
    public Response deleteUser(@PathParam("userid") String userId) {
        this.logger.info(("deleted User id :: " + userId));
        UserDomain userDomain = this.userService.getByUserName(userId);
        if (userDomain == null) {
            message.addErrorMessage("User not found.");
            return Response.ok(ControllerUtils.REDIRECT_TO + "users").build();
        } else {
            this.userService.deleteUser(userDomain);
            return Response.ok(ControllerUtils.REDIRECT_TO + "users").build();
        }
    }

    @POST
    @Path("{userid}")
    @CsrfValid
    @ServiceInvoker(view = "users/view.jsp", model = "user")
    public Response editUser(@BeanParam UserModel user) {
        this.logger.info(("updated User model :: " + user.toString()));
        if (!validateUpdateUser(user)) {
            return Response.ok(ControllerUtils.REDIRECT_TO + "users/" + user.getUserId()).build();
        } else {
            this.userService.updateUser(this.userModelConverter.toDomainObject(user));
            return Response.ok(ControllerUtils.REDIRECT_TO + "users").build();
        }
    }

    @POST
    @Path("new")
    @CsrfValid
    @ServiceInvoker(view = "users/add.jsp", model = "user")
    public Response addNewUser(@BeanParam UserModel user) {
        this.logger.info(("SUBMITTED :: " + user.toString()));
        if (!validateUser(user)) {
            return Response.ok(ControllerUtils.REDIRECT_TO + "users/new").build();
        } else {
            this.userService.addUser(this.userModelConverter.toDomainObject(user));
            this.message.addInfoMessage("User added successfully");
            return Response.ok(ControllerUtils.REDIRECT_TO + "users").build();
        }
    }

    private boolean validateUpdateUser(UserModel user) {
        boolean stat = true;
        if (StringUtils.isBlank(user.getName())) {
            error.addMessage("name", "Field is blank");
            stat = false;
        }
        return stat;
    }

    private boolean validateUser(UserModel user) {
        boolean stat = true;
        if (StringUtils.isBlank(user.getName())) {
            error.addMessage("name", "Field is blank");
            stat = false;
        }
        if (StringUtils.isBlank(user.getUserId())) {
            error.addMessage("userId", "Field is blank");
            stat = false;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            error.addMessage("password", "Field is blank");
            stat = false;
        }
        if (StringUtils.isBlank(user.getConfirmPassword())) {
            error.addMessage("confirmPassword", "Field is blank");
            stat = false;
        }
        return stat;
    }
}
