package pl.bb.broker.brokerdb.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bb.broker.brokerdb.broker.entities.CompaniesEntity;
import pl.bb.broker.brokerdb.broker.entities.OffersEntity;
import pl.bb.broker.brokerdb.broker.entities.RolesEntity;
import pl.bb.broker.brokerdb.broker.entities.UsersEntity;
import pl.bb.broker.security.settings.SecurityGroups;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public enum BrokerDBUtil {
    INSTANCE;

    public void saveUser(UsersEntity user) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            UsersEntity checkUser = (UsersEntity) session.get(UsersEntity.class, user.getUsername());
            if(checkUser!=null) {
                throw new HibernateException("User already exists!");
            }
            RolesEntity role = new RolesEntity();
            role.setUser(user);
            role.setRole(SecurityGroups.STANDARD_USER_GROUP.toString());  //add converter
            user.addRole(role);
            session.save(user);
            tx.commit();
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
    }

    public void saveCompany(CompaniesEntity company) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            UsersEntity checkUser = (UsersEntity) session.get(UsersEntity.class, company.getUser().getUsername());
            if(checkUser!=null) {
                throw new HibernateException("User already exists!");
            }
            RolesEntity role = new RolesEntity();
            role.setUser(company.getUser());
            role.setRole(SecurityGroups.STANDARD_COMPANY_GROUP.toString());  //add converter
            company.getUser().addRole(role);
            session.save(company);
            tx.commit();
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
    }

    public void saveOffer(OffersEntity offer) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(offer);
            tx.commit();
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
    }

    public CompaniesEntity getCompany(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        CompaniesEntity company;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            List<CompaniesEntity> companies = session.createQuery("from CompaniesEntity where user.username = :username")
                    .setParameter("username", username).list();
            if(companies.size()>0) {
                company = companies.get(0);
            } else {
                company = null;
            }
            tx.commit();
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
        return company;
    }

    public OffersEntity getOffer(int id) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        OffersEntity offer;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            offer = (OffersEntity) session.get(OffersEntity.class, id);
            tx.commit();
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
        return offer;
    }

    public boolean userExists(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            UsersEntity checkUser = (UsersEntity) session.get(UsersEntity.class, username);
            tx.commit();
            if(checkUser!=null) {
                return true;
            }
            else {
                return false;
            }
        } catch (HibernateException | ExceptionInInitializerError e) {
            if(tx!=null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if(session!=null) {
                session.close();
            }
        }
    }
}
