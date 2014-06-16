package pl.bb.broker.brokerdb.util;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.bb.broker.brokerdb.broker.entities.*;
import pl.bb.broker.security.settings.SecurityGroups;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 19:19
 * To change this template use File | Settings | File Templates.
 */
public class BrokerDBUtil implements BrokerDBOfferUtil, BrokerDBAuthUtil, BrokerDBReservUtil, BrokerDBInvoiceUtil {

    protected IHibernateUtil hibernateUtil = HibernateConfiguration.getSessionFactory();

    protected BrokerDBUtil() {} //protected - only utils can use it.

    //INSERT

    public void saveUser(UsersEntity user) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            UsersEntity checkUser = (UsersEntity) session.get(UsersEntity.class, user.getUsername());
            if(checkUser!=null) {
                throw new HibernateException("User already exists!");
            }
            user.setActivated(true);
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
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            UsersEntity checkUser = (UsersEntity) session.get(UsersEntity.class, company.getUser().getUsername());
            if(checkUser!=null) {
                throw new HibernateException("User already exists!");
            }
            RolesEntity role = new RolesEntity();
            role.setUser(company.getUser());
            role.setRole(SecurityGroups.STANDARD_COMPANY_GROUP.toString());  //add converter
            company.getUser().addRole(role);
            company.getUser().setActivated(false);
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
            session = hibernateUtil.getSession();
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

    public void saveFavorite(FavoritesEntity favorite) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(favorite);
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

    public void saveReservation(ReservationsEntity reservation) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            session.merge(reservation);
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

    public void saveInvoice(InvoicesEntity invoice) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(invoice);
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

    //UPDATE

    public void updateInvoice(InvoicesEntity invoice) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(invoice);
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

    public void updateReservation(ReservationsEntity reservation) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(reservation);
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

    public void activateUser(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            UsersEntity user = (UsersEntity) session.get(UsersEntity.class, username);
            if(user==null) {
                throw new HibernateException("No such user!");
            }
            user.setActivated(true);
            session.update(user);
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

    //DELETE

    public void removeFavorite(FavoritesEntity favorite) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            session.delete(favorite);
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

    //SELECT

    public CompaniesEntity getCompany(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        CompaniesEntity company;
        try {
            session = hibernateUtil.getSession();
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

    public List<CompaniesEntity> getCompanies() throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<CompaniesEntity> companies;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            companies = session.createQuery("from CompaniesEntity").list();
            for(CompaniesEntity company : companies) {
                Hibernate.initialize(company.getUser());
                Hibernate.initialize(company.getOffers());
                for(OffersEntity offer : company.getOffers()) {
                    Hibernate.initialize(offer.getReservations());
                }
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
        return companies;
    }

    public List<ReservationsEntity> getUnacceptedReservations() throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<ReservationsEntity> reservations;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            reservations = session.createQuery("from ReservationsEntity where accepted is null").list();
            for(ReservationsEntity r : reservations) {
                Hibernate.initialize(r.getOffer());
                Hibernate.initialize(r.getUser());
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
        return reservations;
    }

    public List<ReservationsEntity> getUnacceptedCompanyReservations(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<ReservationsEntity> reservations;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            reservations = session.createQuery("from ReservationsEntity where accepted is null " +
                    "and offer.company.user.username = :username").setParameter("username", username).list();
            for(ReservationsEntity r : reservations) {
                Hibernate.initialize(r.getOffer());
                Hibernate.initialize(r.getUser());
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
        return reservations;
    }

    public List<OffersEntity> getFavorites(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<OffersEntity> offers;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            UsersEntity user = (UsersEntity) session.get(UsersEntity.class, username);
            Hibernate.initialize(user.getFavorites());
//            offers = session.createQuery("select favPK.offer from FavoritesEntity where favPK.user.username = :username")
//                    .setParameter("username", username).list();
            offers = new ArrayList<>();
            for(FavoritesEntity fav : user.getFavorites()) {
                offers.add(fav.getOffer());
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
        return offers;
    }

    public List<InvoicesEntity> getUnpaidInvoices() throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<InvoicesEntity> invoices;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            invoices = session.createQuery("from InvoicesEntity where payment.debtCollection=false and payment.paid=false").list();
            for(InvoicesEntity i : invoices) {
                Hibernate.initialize(i.getPayment());
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
        return invoices;
    }

    public InvoicesEntity getInvoice(String id) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        InvoicesEntity invoice;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            invoice = (InvoicesEntity) session.get(InvoicesEntity.class, id);
            Hibernate.initialize(invoice.getPayment());
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
        return invoice;
    }

    public OffersEntity getOffer(int id) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        OffersEntity offer;
        try {
            session = hibernateUtil.getSession();
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

    public List<OffersEntity> getOffers() throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<OffersEntity> offers = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            offers = session.createQuery("FROM OffersEntity where withdraw is null or withdraw >= :today")
                    .setDate("today", new Date()).list();
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
        return offers;
    }

    public List<OffersEntity> getOffers(String city) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<OffersEntity> offers = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            offers = session.createQuery("FROM OffersEntity where (withdraw is null " +
                    "or withdraw >= :today) and city = :city")
                    .setDate("today", new Date()).setParameter("city", city).list();
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
        return offers;
    }

    public List<OffersEntity> getCompanyOffers(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<OffersEntity> offers = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            offers = session.createQuery("FROM OffersEntity where (withdraw is null " +
                    "or withdraw >= :today) and company.user.username = :username")
                    .setDate("today", new Date()).setParameter("username", username).list();
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
        return offers;
    }

    public List<String> getCities() throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<String> cities = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            cities = session.createQuery("select distinct city FROM OffersEntity where withdraw is null " +
                    "or withdraw >= :today")
                    .setDate("today", new Date()).list();
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
        return cities;
    }

    public List<ReservationsEntity> getUserReservations(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<ReservationsEntity> reserv = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            reserv = session.createQuery("from ReservationsEntity where user.username = :username")
                    .setParameter("username", username).list();
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
        return reserv;
    }

    public List<ReservationsEntity> getUserNewReservations(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        List<ReservationsEntity> reserv = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            reserv = session.createQuery("from ReservationsEntity where user.username = :username " +
                    " and arrival >= :today").setParameter("username", username)
                    .setDate("today", new Date()).list();
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
        return reserv;
    }

    public UsersEntity getUserWithFavorites(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        UsersEntity user = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            user = (UsersEntity) session.get(UsersEntity.class, username);
            Hibernate.initialize(user.getFavorites());
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
        return user;
    }

    public UsersEntity getUser(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        UsersEntity user = null;
        try {
            session = hibernateUtil.getSession();
            tx = session.beginTransaction();
            user = (UsersEntity) session.get(UsersEntity.class, username);
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
        return user;
    }

    public boolean userExists(String username) throws HibernateException {
        Session session = null;
        Transaction tx = null;
        try {
            session = hibernateUtil.getSession();
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
