package pl.bb.broker.brokerdb.util;

import org.hibernate.HibernateException;
import pl.bb.broker.brokerdb.broker.entities.CompaniesEntity;
import pl.bb.broker.brokerdb.broker.entities.UsersEntity;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 29.05.14
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public interface BrokerDBAuthUtil {
    BrokerDBAuthUtil FACTORY = new BrokerDBUtil();

    void saveUser(UsersEntity user) throws HibernateException;
    void saveCompany(CompaniesEntity company) throws HibernateException;
    CompaniesEntity getCompany(String username) throws HibernateException;
    boolean userExists(String username) throws HibernateException;
}
