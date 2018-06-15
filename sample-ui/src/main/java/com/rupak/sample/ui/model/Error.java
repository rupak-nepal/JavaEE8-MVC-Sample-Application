package com.rupak.sample.ui.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.mvc.annotation.RedirectScoped;

/**
 *
 * @author rupak
 */
@RedirectScoped
@Named("error")
public class Error implements Serializable {

    private Map<String, String> fields;

    public Error() {
        this.fields = new HashMap<>();
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
    
    

    public void addMessage(String field, String message) {
        this.fields.put(field, message);
    }

    public boolean isError() {
        return !this.fields.isEmpty();
    }

}
