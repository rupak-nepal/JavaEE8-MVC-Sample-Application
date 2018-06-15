package com.rupak.sample.platform.configs;

/**
 *
 * @author rupak
 */
public class SampleApplicationContextHolder {
    
    private static final ThreadLocal<SampleApplicationContext> context = new ThreadLocal<>();
    
    public static void unSetContext(){
        context.remove();
    }
    
    public static SampleApplicationContext getContext(){
        SampleApplicationContext sampleApplicationContext = context.get();
        return sampleApplicationContext;
    }
    
    public static void setContext(SampleApplicationContext sampleApplicationContext){
        unSetContext();
        context.set(sampleApplicationContext);
    }
}
