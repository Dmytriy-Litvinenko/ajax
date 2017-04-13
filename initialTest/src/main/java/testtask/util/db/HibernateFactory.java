package testtask.util.db;


import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
/*hibernate.cfg.xml
<!--?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
<session-factory name="HibernateSessionFactory">
<property name="dialect">org.hibernate.dialect.MySQLDialect</property>
<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
<property name="connection.url">jdbc:mysql://localhost:3306/hibernate_test</property>
<property name="connection.username">root</property>
<property name="connection.password">root</property>
<property name="hibernate.id.new_generator_mappings">false</property>
<property name="hbm2ddl.auto">update</property>
<property name="hibernate.connection.CharSet">utf8</property>
<property name="hibernate.connection.characterEncoding">utf8</property>
<property name="hibernate.connection.useUnicode">true</property>
<mapping class="testtask.model.Department"/>
<mapping class="testtask.model.Employee"/>
</session-factory>
</hibernate-configuration-->
*/