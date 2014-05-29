import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import pl.bb.broker.brokerdb.broker.entities.OffersEntity;
import pl.bb.broker.brokerdb.broker.xml.XmlCollectionWrapper;
import pl.bb.broker.brokerdb.util.BrokerDBOfferUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
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
public class Test {

    @org.junit.Test
    public void testMarshal() {
        OffersEntity offer = BrokerDBOfferUtil.FACTORY.getOffer(1);
        OffersEntity offer2 = BrokerDBOfferUtil.FACTORY.getOffer(2);

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
//        assert offer.getId()!=1 : "id = "+offer.getId();

    }

    @org.junit.Test
    public void testUnmarshal() throws JAXBException{
        XmlCollectionWrapper<OffersEntity> offers = new XmlCollectionWrapper<>();
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlCollectionWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        offers = (XmlCollectionWrapper<OffersEntity>) unmarshaller.unmarshal(new File("offer.xml"));

        System.out.println(offers.getItems());


    }
}
