package com.rupak.sample.ui.controller;

import com.rupak.sample.platform.exceptions.SampleException;
import com.rupak.sample.service.domains.UserDomain;
import com.rupak.sample.service.userservice.UserService;
import com.rupak.sample.ui.converter.UserModelConverter;
import com.rupak.sample.ui.model.CurrentUser;
import com.rupak.sample.ui.model.Message;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.mvc.binding.BindingResult;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import com.rupak.sample.ui.model.Error;
import com.rupak.sample.ui.utils.ControllerUtils;
import com.rupak.sample.ui.utils.WebSecurityUtils;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author rupak
 */
@Controller
@Path("/")
@Named
@RequestScoped
public class IndexController {

    private static final Logger logger = Logger.getLogger(IndexController.class.getName());

    private Message message;
    private Models indexModels;
    private BindingResult bindingResult;
    private Error error;
    private UserService userService;
    private UserModelConverter userModelConverter;
    private WebSecurityUtils webSecurityUtils;

    public IndexController() {
    }

    @Inject
    public IndexController(Message message, Models indexModels, BindingResult bindingResult, Error error, UserService userService, UserModelConverter userModelConverter, WebSecurityUtils webSecurityUtils) {
        this.message = message;
        this.indexModels = indexModels;
        this.bindingResult = bindingResult;
        this.error = error;
        this.userService = userService;
        this.userModelConverter = userModelConverter;
        this.webSecurityUtils = webSecurityUtils;
    }
    
    @GET
    @View("login.jsp")
    public void displayLoginPage() {
    }

    @GET
    @Path("/home")
    @View("home.jsp")
    public void getHomePage() {
    }

    @GET
    @Path("/login")
    public Response getLoginPage() {
        return Response.ok("login.jsp").build();
    }
    
    @POST
    @Path("/login")
    public Response onLogin(@FormParam("username") String useranme,
            @FormParam("password") String password){
     try {
            UserDomain userDomain = this.userService.authenticateUser(useranme, password);
            CurrentUser currentUser = new CurrentUser(
                    userDomain.getName(), userDomain.getUserId());
            webSecurityUtils.createAndPutInSession(currentUser);
            return Response.ok(ControllerUtils.REDIRECT_TO+"home").build();
        } catch (SampleException ex) {
            ControllerUtils.addErrorMessage(this.indexModels, ex.getExceptionType().getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("login.jsp").build();
    }
    
    @GET
    @Path("logout")
    public Response logout() {
        logger.info("I am inside logout.");
        this.webSecurityUtils.invalidateSession();
        return Response.ok(ControllerUtils.REDIRECT_TO + "login").build();
    }
}
