package pl.bb.broker.brokerdb.broker.xml;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import pl.bb.broker.brokerdb.broker.entities.OffersEntity;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 29.05.14
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "list")
@XmlSeeAlso(OffersEntity.class)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class XmlCollectionWrapper<T> {
    protected List<T> items;

    @XmlAnyElement(lax = true)
    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
