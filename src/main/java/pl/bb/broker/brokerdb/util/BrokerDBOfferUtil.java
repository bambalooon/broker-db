package pl.bb.broker.brokerdb.util;

import org.hibernate.HibernateException;
import pl.bb.broker.brokerdb.broker.entities.CompaniesEntity;
import pl.bb.broker.brokerdb.broker.entities.FavoritesEntity;
import pl.bb.broker.brokerdb.broker.entities.OffersEntity;
import pl.bb.broker.brokerdb.broker.entities.UsersEntity;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 29.05.14
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public interface BrokerDBOfferUtil {
    BrokerDBOfferUtil FACTORY = new BrokerDBUtil();

    //INSERT
    void saveOffer(OffersEntity offer) throws HibernateException;
    void saveFavorite(FavoritesEntity favorite) throws HibernateException;
    //UPDATE

    //DELETE
    void removeFavorite(FavoritesEntity favorite) throws HibernateException;

    //SELECT
    public UsersEntity getUserWithFavorites(String username) throws HibernateException;
    public List<OffersEntity> getFavorites(String username) throws HibernateException;
    public List<String> getCities() throws HibernateException;
    OffersEntity getOffer(int id) throws HibernateException;
    public List<OffersEntity> getOffers(String city) throws HibernateException;
    public List<OffersEntity> getCompanyOffers(String username) throws HibernateException;
    public List<OffersEntity> getOffers() throws HibernateException;
    //CONNECTED
    CompaniesEntity getCompany(String username) throws HibernateException;
}
