package pl.bb.broker.brokerdb.util;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 30.05.14
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
public class HibernateConfiguration {
    private static IHibernateUtil sessionFactory;
    public static IHibernateUtil getSessionFactory() {
        if(sessionFactory==null) {
            sessionFactory = HibernateUtil.getInstance();
        }
        return sessionFactory;
    }
}
