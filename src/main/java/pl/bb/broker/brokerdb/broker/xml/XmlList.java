package pl.bb.broker.brokerdb.broker.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 15.06.14
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "list")
public class XmlList<T> {
    protected List<T> list;

    public XmlList() {}

    public XmlList(List<T> list) {
        this.list = list;
    }

    @XmlElement(name = "item")
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
