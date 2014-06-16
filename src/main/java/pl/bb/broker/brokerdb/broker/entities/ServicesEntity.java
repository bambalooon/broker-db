package pl.bb.broker.brokerdb.broker.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 07.06.14
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "service")
@javax.persistence.Table(name = "services", schema = "public", catalog = "broker")
@Entity
public class ServicesEntity {
    private int id;
    @NotNull
    @Size(min = 1, max = 40)
    private String name;
    @Min(value = 1)
    private Integer quantity;
    @Size(min = 1, max = 10)
    private String unit;
    @DecimalMin("0.00")
    private BigDecimal unitPrice;
    @NotNull
    @Min(0)
    private int vat;
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal linePrice;

    private InvoicesEntity invoice;

    @XmlTransient
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @SequenceGenerator(name = "services_id_seq", sequenceName = "services_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "services_id_seq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    @javax.persistence.Column(name = "quantity", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @XmlElement
    @javax.persistence.Column(name = "unit", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlElement
    @javax.persistence.Column(name = "unit_price", nullable = true, insertable = true, updatable = true, length = 7, precision = 2)
    @Basic
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @XmlElement
    @javax.persistence.Column(name = "vat", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    @XmlElement
    @javax.persistence.Column(name = "line_price", nullable = false, insertable = true, updatable = true, length = 7, precision = 2)
    @Basic
    public BigDecimal getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(BigDecimal linePrice) {
        this.linePrice = linePrice;
    }

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice")
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

        ServicesEntity that = (ServicesEntity) o;

        if (id != that.id) return false;
        if (vat != that.vat) return false;
        if (linePrice != null ? !linePrice.equals(that.linePrice) : that.linePrice != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + vat;
        result = 31 * result + (linePrice != null ? linePrice.hashCode() : 0);
        return result;
    }
}
