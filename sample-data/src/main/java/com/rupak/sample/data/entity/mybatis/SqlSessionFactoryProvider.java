package com.rupak.sample.data.entity.mybatis;

import java.io.Reader;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;

/**
 *
 * @author rupak
 */
public class SqlSessionFactoryProvider {
    private static String CONFIGURATION_FILE = "mybatis-config.xml";      
    
    
    @ApplicationScoped
    @Produces
    @SessionFactoryProvider
    public SqlSessionFactory produce() throws Exception {
        Reader reader = Resources.getResourceAsReader(CONFIGURATION_FILE);
        SqlSessionFactory manager = new SqlSessionFactoryBuilder().build(reader);
        reader.close();
        return manager;
    }
}
