package pl.bb.broker.brokerdb.util;

import org.hibernate.HibernateException;
import pl.bb.broker.brokerdb.broker.entities.OffersEntity;
import pl.bb.broker.brokerdb.broker.entities.ReservationsEntity;
import pl.bb.broker.brokerdb.broker.entities.UsersEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 05.06.14
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
public interface BrokerDBReservUtil {
    BrokerDBReservUtil FACTORY = new BrokerDBUtil();

    //INSERT
    void saveReservation(ReservationsEntity reservation) throws HibernateException;

    //UPDATE
    void updateReservation(ReservationsEntity reservation) throws HibernateException;

    //SELECT
    List<ReservationsEntity> getUnacceptedCompanyReservations(String username) throws HibernateException;
    List<ReservationsEntity> getUserReservations(String username) throws HibernateException;
    List<ReservationsEntity> getUserNewReservations(String username) throws HibernateException;
    List<ReservationsEntity> getUnacceptedReservations() throws HibernateException;
    OffersEntity getOffer(int id) throws HibernateException;
    UsersEntity getUser(String username) throws HibernateException;
}
