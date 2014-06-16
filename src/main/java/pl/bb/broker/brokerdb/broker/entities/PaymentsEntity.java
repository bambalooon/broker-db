package pl.bb.broker.brokerdb.broker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 07.06.14
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "payment")
@javax.persistence.Table(name = "payments", schema = "public", catalog = "broker")
@Entity
public class PaymentsEntity {
    @NotNull
    @Size(min = 1, max = 40)
    private String invoiceId;
    @NotNull
    private boolean paid;
    @NotNull
    private boolean debtCollection;

    private InvoicesEntity invoice;

    @XmlAttribute
    @javax.persistence.Column(name = "invoice_id", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Id
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @XmlElement
    @javax.persistence.Column(name = "paid", nullable = false, insertable = true, updatable = true)
    @Basic
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @XmlTransient
    @javax.persistence.Column(name = "debt_collection", nullable = false, insertable = true, updatable = true)
    @Basic
    public boolean isDebtCollection() {
        return debtCollection;
    }

    public void setDebtCollection(boolean debtCollection) {
        this.debtCollection = debtCollection;
    }

    @XmlTransient
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    public InvoicesEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoicesEntity invoice) {
        this.invoice = invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentsEntity that = (PaymentsEntity) o;

        if (paid != that.paid) return false;
        if (invoiceId != null ? !invoiceId.equals(that.invoiceId) : that.invoiceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = invoiceId != null ? invoiceId.hashCode() : 0;
        result = 31 * result + (paid ? 1 : 0);
        return result;
    }
}
