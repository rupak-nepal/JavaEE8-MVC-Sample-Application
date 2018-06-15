package com.rupak.sample.platform.configs;

/**
 *
 * @author rupak
 */
public class SampleApplicationContext {
    
    private String currentUser;

    public SampleApplicationContext(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
    
}
