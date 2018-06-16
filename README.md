# JavaEE8-MVC-Sample-Application
Java EE8, CDI, Payera 5, Apache Mybatis, and Posgtres.


### How do I get set up? ###

* First clone project and perform build. 
* payara5/glassfish/domains/domain1/config/domain.xml(update with :)
<jdbc-connection-pool datasource-classname="org.postgresql.ds.PGSimpleDataSource" name="postgresqsapleapplpool" res-type="javax.sql.DataSource">
      <property name="Password" value="password_sample"></property>
      <property name="PortNumber" value="5432"></property>
      <property name="ServerName" value="localhost"></property>
      <property name="Url" value="jdbc:postgresql://localhost/sample_dataabaasse"></property>
      <property name="User" value="test"></property>
      <property name="DatabaseMetadataCacheFieldsMiB" value="5"></property>
      <property name="ApplicationName" value="PostgreSQL JDBC Driver"></property>
      <property name="DatabaseName" value="sample_dataabaasse"></property>
    </jdbc-connection-pool>
    <jdbc-resource pool-name="postgresqsapleapplpool" jndi-name="jdbc/postgres"></jdbc-resource>
<server>
 <resource-ref ref="jdbc/postgres"></resource-ref>
</server>
* put postgresql-42.2.2.jar inside payara5/glassfish/domains/domain1/lib/
* Then insert a column in users table with full_name, username and password.
* Then start server.
* Then you can log in.
