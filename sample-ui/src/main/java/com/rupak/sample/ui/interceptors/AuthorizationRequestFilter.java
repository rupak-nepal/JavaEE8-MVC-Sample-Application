package com.rupak.sample.ui.interceptors;

import com.rupak.sample.ui.utils.ControllerUtils;
import com.rupak.sample.ui.utils.WebSecurityUtils;
import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author rupak
 */
@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(AuthorizationRequestFilter.class.getName());

    @Inject
    private WebSecurityUtils webSecurityUtils;

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        if (!this.webSecurityUtils.isPublicUrl(crc.getUriInfo().getPath())) {
            if (!this.webSecurityUtils.hasUserLoggedIn()) {
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity(ControllerUtils.REDIRECT_TO + "login").build());
            }
        }
    }

}
