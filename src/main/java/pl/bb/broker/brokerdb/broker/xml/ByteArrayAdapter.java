package pl.bb.broker.brokerdb.broker.xml;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 29.05.14
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class ByteArrayAdapter extends XmlAdapter<String, byte[]> {

    public String marshal(byte[] array) {
        return DatatypeConverter.printBase64Binary(array);
    }

    public byte[] unmarshal(String encodedArray) {
        return DatatypeConverter.parseBase64Binary(encodedArray);
    }
}
