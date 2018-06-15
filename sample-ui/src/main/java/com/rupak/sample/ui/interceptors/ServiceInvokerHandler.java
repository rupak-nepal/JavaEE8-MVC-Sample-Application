package com.rupak.sample.ui.interceptors;

import com.rupak.sample.platform.configs.SampleApplicationContext;
import com.rupak.sample.platform.configs.SampleApplicationContextHolder;
import com.rupak.sample.platform.exceptions.SampleException;
import com.rupak.sample.ui.model.Message;
import com.rupak.sample.ui.model.ServiceResposne;
import com.rupak.sample.ui.utils.WebSecurityUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.mvc.Models;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rupak
 */
@ServiceInvoker
@Interceptor
public class ServiceInvokerHandler implements Serializable {

    private static final Logger logger = Logger.getLogger(ServiceInvokerHandler.class.getName());

    @Inject
    private Message message;
    @Inject
    private WebSecurityUtils webSecurityUtils;
    @Inject
    private Models models;

    @AroundInvoke
    public Object process(InvocationContext context) throws Exception {
        ServiceInvoker serviceInvoker = this.getHandlerExceptionAnnotation(context);
        try {
            SampleApplicationContext sampleApplicationContext = null;
            if (webSecurityUtils.hasUserLoggedIn()) {
                sampleApplicationContext = new SampleApplicationContext(
                        webSecurityUtils.getCurrentUser().getUserName());
            }
            logger.info("Executing :" + context.getMethod().getName());
            SampleApplicationContextHolder.setContext(sampleApplicationContext);
            return context.proceed();
        } catch (SampleException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            message.addErrorMessage(ex.getExceptionType().getMessage());
            if (!StringUtils.isBlank(serviceInvoker.view())) {
                if (!StringUtils.isBlank(serviceInvoker.model())) {
                    this.models.put(serviceInvoker.model(),
                            context.getParameters()[serviceInvoker.modelIndex()]);
                }
                return Response.ok(serviceInvoker.view()).build();
            } else {
                ServiceResposne serviceResposne = new ServiceResposne(ex.getMessage(), context.getParameters()[serviceInvoker.modelIndex()]);
                return Response.status(Response.Status.BAD_REQUEST).entity(serviceResposne).build();
            }
        } finally {
            SampleApplicationContextHolder.unSetContext();
        }
    }
    
    private ServiceInvoker getHandlerExceptionAnnotation(InvocationContext context) {
        ServiceInvoker serviceInvoker = context.getMethod().getAnnotation(ServiceInvoker.class);
        if (serviceInvoker == null) {
            serviceInvoker = context.getMethod().getDeclaringClass().getAnnotation(ServiceInvoker.class);
        }
        return serviceInvoker;
    }

}
