package pl.bb.broker.brokerdb.util;

import org.hibernate.HibernateException;
import pl.bb.broker.brokerdb.broker.entities.CompaniesEntity;
import pl.bb.broker.brokerdb.broker.entities.InvoicesEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 07.06.14
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public interface BrokerDBInvoiceUtil {
    BrokerDBInvoiceUtil FACTORY = new BrokerDBUtil();

    //INSERT
    void saveInvoice(InvoicesEntity invoice) throws HibernateException;

    //UPDATE
    void updateInvoice(InvoicesEntity invoice) throws HibernateException;
    void activateUser(String username) throws HibernateException;

    //SELECT
    InvoicesEntity getInvoice(String id) throws HibernateException;
    List<InvoicesEntity> getUnpaidInvoices() throws HibernateException;
    List<CompaniesEntity> getCompanies() throws HibernateException;
}
