package com.rupak.sample.ui.configuration;

import java.util.HashMap;
import java.util.Map;
import javax.mvc.security.Csrf;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author rupak
 */
@ApplicationPath("/app")
public class MvcConfiguration extends Application{
    @Override
    public Map<String, Object> getProperties() {
        final Map<String, Object> map = new HashMap<>();
        map.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.EXPLICIT);
        map.put("org.mvcspec.ozark.redirectScopeCookies", true);
        return map;
    }
}
