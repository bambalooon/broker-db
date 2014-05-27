package pl.bb.broker.brokerdb.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 01:46
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;


    static {
        try {
            Configuration conf = new Configuration();
            conf.configure();

            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(conf.getProperties());
            StandardServiceRegistry registry = sb.build();
            SESSION_FACTORY = conf.buildSessionFactory(registry);
        } catch (Throwable th) {
            throw new ExceptionInInitializerError(th);
        }
    }

    public static Session getSession() throws HibernateException {
        return SESSION_FACTORY.openSession();
    }

}
