package com.rupak.sample.platform.configs;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author rupak
 */
@Named
@ApplicationScoped
public class AppConfig {

    public AppConfig() {
    }
    
    public String getApplicationName(){
        return "Sample Application";
    }
    
}
