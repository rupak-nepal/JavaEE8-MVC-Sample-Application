package com.rupak.sample.ui.model;

/**
 *
 * @author rupak
 */
public class ServiceResposne {
    private String message;
    private Object data=null;

    public ServiceResposne() {
    }

    public ServiceResposne(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
}
