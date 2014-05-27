package pl.bb.broker.brokerdb.broker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 26.05.14
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "companies", schema = "public", catalog = "broker")
@Entity
public class CompaniesEntity {
    private int id;
    private UsersEntity user;
    @NotNull
    private String companyname;
    private String address;
    private String phone;
    private Collection<OffersEntity> offers;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    @javax.persistence.Column(name = "companyname", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @javax.persistence.Column(name = "address", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @javax.persistence.Column(name = "phone", nullable = true, insertable = true, updatable = true, length = 12, precision = 0)
    @Basic
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user.getUsername() != null ? user.getUsername().hashCode() : 0);
        result = 31 * result + (companyname != null ? companyname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}