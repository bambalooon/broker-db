package pl.bb.broker.brokerdb.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 30.05.14
 * Time: 13:05
 * To change this template use File | Settings | File Templates.
 */
public interface IHibernateUtil {
    Session getSession() throws HibernateException;
}
