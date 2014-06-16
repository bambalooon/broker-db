package pl.bb.broker.brokerdb.broker.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 26.05.14
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "company")
@javax.persistence.Table(name = "companies", schema = "public", catalog = "broker")
@Entity
public class CompaniesEntity {
    private int id;
    private UsersEntity user;
    @NotNull
    private String companyname;
    private String address;
    private String phone;
    @Size(max = 100)
    private String resources;
    @NotNull
    @Email
    @Size(max = 100)
    private String email;
    private Collection<OffersEntity> offers;

    @XmlAttribute
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = false, length = 10, precision = 0)
    @Id
    @SequenceGenerator(name = "companies_id_seq", sequenceName = "companies_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_id_seq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //ignore null because there can be none company for specific username...
    @XmlTransient //not nessesary
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    @XmlElement
    @javax.persistence.Column(name = "companyname", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @XmlElement
    @javax.persistence.Column(name = "address", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement
    @javax.persistence.Column(name = "phone", nullable = true, insertable = true, updatable = true, length = 12, precision = 0)
    @Basic
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement
    @javax.persistence.Column(name = "resources", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    @XmlElement
    @javax.persistence.Column(name = "email", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient //i don't want to :(
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    public Collection<OffersEntity> getOffers() {
        return offers;
    }

    public void setOffers(Collection<OffersEntity> offers) {
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompaniesEntity that = (CompaniesEntity) o;

        if (id != that.id) return false;
        if (user.getUsername() != null ? !user.getUsername().equals(that.getUser().getUsername()) : that.getUser() != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (companyname != null ? !companyname.equals(that.companyname) : that.companyname != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (email != null ? email.equals(that.email) : that.email != null) return false;
        if (resources != null ? resources.equals(that.resources) : that.resources != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user.getUsername() != null ? user.getUsername().hashCode() : 0);
        result = 31 * result + (companyname != null ? companyname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (resources != null ? resources.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company[id="+id+"] name: "+companyname+"\n"+address+"\n"+phone+"\n";
    }
}
