package pl.bb.broker.brokerdb.broker.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 07.06.14
 * Time: 01:44
 * To change this template use File | Settings | File Templates.
 */
public class SqlDateAdapter extends XmlAdapter<java.util.Date, java.sql.Date> {

    @Override
    public java.util.Date marshal(java.sql.Date sqlDate) throws Exception {
        if(null == sqlDate) {
            return null;
        }
        return new java.util.Date(sqlDate.getTime());
    }

    @Override
    public java.sql.Date unmarshal(java.util.Date utilDate) throws Exception {
        if(null == utilDate) {
            return null;
        }
        return new java.sql.Date(utilDate.getTime());
    }
}
