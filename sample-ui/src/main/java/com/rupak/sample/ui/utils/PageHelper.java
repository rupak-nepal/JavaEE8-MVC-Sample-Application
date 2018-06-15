package com.rupak.sample.ui.utils;

import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.mvc.MvcContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rupak
 */
@Model
public class PageHelper {

    private static final Logger logger = Logger.getLogger(PageHelper.class.getName());

    @Inject
    private HttpServletRequest request;
    @Inject
    private MvcContext mvcContext;

    public boolean isActiveResource(String[] conditions) {

        String url = request.getRequestURL().toString();
        String[] val = StringUtils.split(request.getRequestURL().toString(), "/");
        String preFix = val[0] + "//" + val[1] + mvcContext.getBasePath() + "/";
        for (String str : conditions) {
            logger.info("full :" + preFix + str);
            if (url.equalsIgnoreCase(preFix + str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isActiveGroupResource(String[] conditions) {
        String url = request.getRequestURL().toString();
        for (String str : conditions) {
            if (url.contains(str)) {
                return true;
            }
        }
        return false;

    }
}
