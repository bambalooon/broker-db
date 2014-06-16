package pl.bb.broker.brokerdb.util;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pl.bb.broker.brokerdb.broker.entities.OffersEntity;
import pl.bb.broker.brokerdb.broker.entities.ReservationsEntity;
import pl.bb.broker.brokerdb.broker.xml.XmlCollectionWrapper;
import pl.bb.broker.brokerdb.util.BrokerDBOfferUtil;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 29.05.14
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOffer {


    public void testMarshal() throws NoSuchFieldException, IllegalAccessException {
        Field field = HibernateConfiguration.class.getDeclaredField("sessionFactory");
        field.setAccessible(true);
        field.set(null, TestHibernateUtil.getInstance());

        OffersEntity offer = BrokerDBOfferUtil.FACTORY.getOffer(9);
        OffersEntity offer2 = BrokerDBOfferUtil.FACTORY.getOffer(11);

        try {
            XmlCollectionWrapper<OffersEntity> xmlOffers = new XmlCollectionWrapper<>();
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlCollectionWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            List<OffersEntity> offers = new ArrayList<>();
            offers.add(offer);
            offers.add(offer2);
            xmlOffers = new XmlCollectionWrapper<>();
            xmlOffers.setItems(offers);
            marshaller.marshal(xmlOffers, System.out);
            marshaller.marshal(xmlOffers, new File("offer.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public void testUnmarshal() throws JAXBException{
        XmlCollectionWrapper<JAXBElement<String>> offers = new XmlCollectionWrapper<>();
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlCollectionWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        offers = (XmlCollectionWrapper<JAXBElement<String>>) unmarshaller.unmarshal(new File("cities.xml"));

        System.out.println(offers.getItems());


    }
    @Test
    public void testR() throws Exception {
        Field field = HibernateConfiguration.class.getDeclaredField("sessionFactory");
        field.setAccessible(true);
        field.set(null, TestHibernateUtil.getInstance());
        List<OffersEntity> offers = BrokerDBOfferUtil.FACTORY.getFavorites("luffy");
        System.out.println(offers.size());

    }

    public void testCity() throws Exception {
        Field field = HibernateConfiguration.class.getDeclaredField("sessionFactory");
        field.setAccessible(true);
        field.set(null, TestHibernateUtil.getInstance());
        List<OffersEntity> offers = BrokerDBOfferUtil.FACTORY.getOffers("Gdańsk");
        System.out.println(offers);
    }

    public void testCities() throws  Exception {
        Field field = HibernateConfiguration.class.getDeclaredField("sessionFactory");
        field.setAccessible(true);
        field.set(null, TestHibernateUtil.getInstance());
        List<String> cities = BrokerDBOfferUtil.FACTORY.getCities();
        System.out.println(cities);
        XmlCollectionWrapper<String> xmlOffers = new XmlCollectionWrapper<>();
        xmlOffers.setItems(cities);
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlCollectionWrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(xmlOffers, System.out);


//        List<JAXBElement<String>> list = new ArrayList<>();
//        QName qname = new QName("", "city");
//        for(String city : cities) {
//            list.add(new JAXBElement<String>(qname, String.class, city));
//        }
//        System.out.println(list);
//        XmlCollectionWrapper<JAXBElement<String>> xmlOffers = new XmlCollectionWrapper<>();
//        xmlOffers.setItems(list);




    }
}
