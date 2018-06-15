package com.rupak.sample.ui.utils;

import javax.mvc.Models;
/**
 *
 * @author rupak
 */
public class ControllerUtils {
    
    public static final String REDIRECT_TO="redirect:";
    public static final String ERROR_MSG="ERROR_MSG";
    public static final String SUCCESS_MSG="SUCCESS_MSG";

    public ControllerUtils() {
    }
    
    public static void addSuccessMessage(Models models,String message){
        models.put(SUCCESS_MSG, message);
    }
    
    public static void addErrorMessage(Models models,String message){
        models.put(ERROR_MSG, message);
    }
}
