package com.rupak.sample.ui.utils;

import com.rupak.sample.ui.model.CurrentUser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mvc.MvcContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rupak
 */
@Named
@RequestScoped
public class WebSecurityUtils {

    private static String CURRENT_USER = "current_user";

    @Inject
    private HttpServletRequest request;
    @Inject
    private MvcContext mvcContext;

    public void createAndPutInSession(CurrentUser currentUser) {
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }
        request.getSession(true).setAttribute(CURRENT_USER, currentUser);
    }

    public CurrentUser getCurrentUser() {
        HttpSession session = this.request.getSession(false);
        if (session == null) {
            return null;
        }
        Object value = request.getSession(false).getAttribute(CURRENT_USER);
        if (value == null) {
            return null;
        }
        return (CurrentUser) value;
    }

    public void updateCurrentUser(CurrentUser currentUser) {
        HttpSession session = this.request.getSession(false);
        if (session != null) {
            session.setAttribute(CURRENT_USER, currentUser);
        }
    }

    public void invalidateSession() {
        request.getSession(false).invalidate();
    }

    public boolean hasUserLoggedIn() {
        return getCurrentUser() == null ? false : true;
    }

    public List<String> getPublicAccessurls() {
        List<String> list = new ArrayList<>();
        list.add("login");
        list.add("logout");
        list.add("static");
        list.add("");
        return list;
    }

    public boolean isPublicUrl(String url) {
        List<String> data = this.getPublicAccessurls().stream().filter(
                ele -> ele.equalsIgnoreCase(url)).collect(Collectors.toList());
        return !data.isEmpty();
    }

}
