package com.rupak.sample.ui.model;

import java.io.Serializable;
import javax.inject.Named;
import javax.mvc.annotation.RedirectScoped;

/**
 *
 * @author rupak
 */
@Named("message")
@RedirectScoped
public class Message implements Serializable{
    private String text;
    private boolean error;

    public Message() {
        this.error = false;
        this.text="";
    }

    public Message(String text, boolean error) {
        this.text = text;
        this.error = error;
    }

    public String getText() {
        return text;
    }
    
    public void addInfoMessage(String msg){
        this.text = msg;
        this.error = false;
    }
    
    public void addErrorMessage(String msg){
        this.text = msg;
        this.error = true;
    }
    
    public boolean isError(){
        return error;
    }
    
    
}
