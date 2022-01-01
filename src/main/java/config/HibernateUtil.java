package config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                {
                    Configuration configuration = new org.hibernate.cfg.Configuration();
                    Properties setting = new Properties();
                    setting.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                    setting.put(Environment.URL, "jdbc:mysql://localhost:3306/servlet");
                    setting.put(Environment.USER, "root");
                    setting.put(Environment.PASS, "1234567890");
                    setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                    setting.put(Environment.SHOW_SQL, "true");
                    setting.put(Environment.HBM2DDL_AUTO, "update");

                    configuration.setProperties(setting);
                    //configuration.addAnnotatedClass(User.class);
                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
