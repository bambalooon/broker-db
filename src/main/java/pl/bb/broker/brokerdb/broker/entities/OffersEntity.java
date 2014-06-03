package pl.bb.broker.brokerdb.broker.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import pl.bb.broker.brokerdb.broker.xml.ByteArrayAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 26.05.14
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "offer")
@javax.persistence.Table(name = "offers", schema = "public", catalog = "broker")
@Entity
public class OffersEntity implements Serializable {
    private int id;
    @NotNull
    @Size(min = 1, max = 20)
    private String facility;
    @NotNull
    @Size(min = 1)
    private String description;
    @NotNull
    private byte[] image;
    @NotNull
    @Size(min = 1, max = 40)
    private String city;
    private CompaniesEntity company;
    private Collection<OfferDetailsEntity> details;

    @XmlAttribute
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable =false, length = 10, precision = 0)
    @Id
    @SequenceGenerator(name = "offers_id_seq", sequenceName = "offers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offers_id_seq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER) //no cascade
    @JoinColumn(name = "company_id")
    public CompaniesEntity getCompany() {
        return company;
    }

    public void setCompany(CompaniesEntity company) {
        this.company = company;
    }

    @XmlElement
    @javax.persistence.Column(name = "facility", nullable = false, insertable = true, updatable = true, precision = 0)
    @Basic
    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    @XmlElement
    @javax.persistence.Column(name = "description", nullable = false, insertable = true, updatable = true, precision = 0)
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    @XmlJavaTypeAdapter(value = ByteArrayAdapter.class)
    @javax.persistence.Column(name = "image", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Fetch(FetchMode.JOIN)
    @Basic
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @XmlElement
    @javax.persistence.Column(name = "city", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name = "detail")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "offerDetailsPK.offer")
    public Collection<OfferDetailsEntity> getDetails() {
        return details;
    }

    public void setDetails(Collection<OfferDetailsEntity> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OffersEntity that = (OffersEntity) o;

        if (id != that.id) return false;
        if(company != that.getCompany()) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!Arrays.equals(image, that.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (company != null ? company.getCompanyname().hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? Arrays.hashCode(image) : 0);
        return result;
    }

    @Override
    public String toString() {
        String result = "offer[id="+id+"]\n"+description+"\n"+city+"\n"+company;
        for(OfferDetailsEntity d : details) {
            result += "detail: "+d.getRoom()+" - "+d.getPrice()+"\n";
        }
        return result;
    }

}
