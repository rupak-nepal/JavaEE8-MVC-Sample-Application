package com.rupak.sample.platform.common.security;

/**
 *
 * @author rupak
 */
public class UnAuthorizedException extends RuntimeException{
    
    public UnAuthorizedException(){
        super("User does not have sufficient permission.");
    }
    
}
