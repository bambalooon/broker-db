package pl.bb.broker.brokerdb.util;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import pl.bb.broker.brokerdb.broker.entities.OfferDetailsEntity;
import pl.bb.broker.brokerdb.broker.entities.OffersEntity;
import pl.bb.broker.brokerdb.broker.entities.ReservationsEntity;
import pl.bb.broker.brokerdb.broker.entities.UsersEntity;
import pl.bb.broker.brokerdb.broker.xml.XmlCollectionWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 29.05.14
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestReserve {

    @org.junit.Test
    public void testReserve() throws NoSuchFieldException, IllegalAccessException {
        Field field = HibernateConfiguration.class.getDeclaredField("sessionFactory");
        field.setAccessible(true);
        field.set(null, TestHibernateUtil.getInstance());

        OffersEntity offer = BrokerDBReservUtil.FACTORY.getOffer(9);
        UsersEntity user = BrokerDBReservUtil.FACTORY.getUser("luffy");

        OfferDetailsEntity room = new OfferDetailsEntity();
        room.setOffer(offer);
        room.setRoomType("N10");

        ReservationsEntity reservation = new ReservationsEntity();
        reservation.setArrival(new Date(0L));
        reservation.setDeparture(new Date(0L));
        reservation.setUser(user);
        reservation.setOffer(offer);
        reservation.setRoom(room);

        BrokerDBReservUtil.FACTORY.saveReservation(reservation);
    }

}
