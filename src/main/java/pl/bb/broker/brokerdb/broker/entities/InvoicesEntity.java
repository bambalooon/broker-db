package pl.bb.broker.brokerdb.broker.entities;

import pl.bb.broker.brokerdb.broker.xml.SqlDateAdapter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 07.06.14
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "invoice")
@javax.persistence.Table(name = "invoices", schema = "public", catalog = "broker")
@Entity
public class InvoicesEntity {
    @Size(min = 1, max = 40)
    @NotNull
    private String id;
    @Size(min = 1)
    @NotNull
    private String seller;
    @Size(min = 1)
    @NotNull
    private String buyer;
    @NotNull
    private Date date;
    @Size(min = 1, max = 20)
    @NotNull
    private String formOfPayment;
    private Date dueDate;
    @NotNull
    private BigDecimal total;

    private Collection<ServicesEntity> services;
    private PaymentsEntity payment;

    @XmlAttribute
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    @javax.persistence.Column(name = "seller", nullable = false, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @XmlElement
    @javax.persistence.Column(name = "buyer", nullable = false, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    @XmlElement
    @XmlJavaTypeAdapter(value = SqlDateAdapter.class)
    @javax.persistence.Column(name = "date", nullable = false, insertable = true, updatable = true, length = 13, precision = 0)
    @Basic
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement
    @javax.persistence.Column(name = "form_of_payment", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(String formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    @XmlElement
    @XmlJavaTypeAdapter(value = SqlDateAdapter.class)
    @javax.persistence.Column(name = "due_date", nullable = true, insertable = true, updatable = true, length = 13, precision = 0)
    @Basic
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @XmlElement
    @javax.persistence.Column(name = "total", nullable = false, insertable = true, updatable = true, length = 7, precision = 2)
    @Basic
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "invoice")
    public Collection<ServicesEntity> getServices() {
        return services;
    }

    public void setServices(Collection<ServicesEntity> services) {
        this.services = services;
    }

    @XmlElement
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoice")
    public PaymentsEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentsEntity payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoicesEntity that = (InvoicesEntity) o;

        if (buyer != null ? !buyer.equals(that.buyer) : that.buyer != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (formOfPayment != null ? !formOfPayment.equals(that.formOfPayment) : that.formOfPayment != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (seller != null ? !seller.equals(that.seller) : that.seller != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        result = 31 * result + (buyer != null ? buyer.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (formOfPayment != null ? formOfPayment.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }
}
