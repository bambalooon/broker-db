package pl.bb.broker.brokerdb.broker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.Cascade;
import pl.bb.broker.security.settings.SecuritySettings;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 02:05
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "user")
@javax.persistence.Table(name = "users", schema = "public", catalog = "broker")
@Entity
public class UsersEntity {
    @Size(min = SecuritySettings.USERNAME_MIN, max = SecuritySettings.USERNAME_MAX)
    @Pattern(regexp = SecuritySettings.USERNAME_PATTERN)
    @NotNull
    private String username;

    @Size(min = SecuritySettings.PASSWORDHASH_LEN, max = SecuritySettings.PASSWORDHASH_LEN)
    @NotNull
    private String password;
    @Size(min = 1, max = 40)
    @NotNull
    private String firstname;
    @Size(min = 1, max = 40)
    @NotNull
    private String surname;
    @NotNull
    private boolean activated;

    private Collection<RolesEntity> roles;
    private Collection<CompaniesEntity> companies;
    private Collection<ReservationsEntity> reservations;
    private Collection<FavoritesEntity> favorites;

    @XmlElement
    @javax.persistence.Column(name = "username", nullable = false, insertable = true, updatable = true, length = SecuritySettings.USERNAME_MAX, precision = 0)
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    @javax.persistence.Column(name = "password", nullable = false, insertable = true, updatable = true, length = SecuritySettings.PASSWORDHASH_LEN, precision = 0)
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    @javax.persistence.Column(name = "firstname", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @XmlElement
    @javax.persistence.Column(name = "surname", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlElement
    @javax.persistence.Column(name = "activated", nullable = false, insertable = true, updatable = true)
    @Basic
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }


    @XmlElement
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "rolePK.user")
    public Collection<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RolesEntity> roles) {
        this.roles = roles;
    }

    public void addRole(RolesEntity role) {
        if(roles==null) {
            roles = new ArrayList<RolesEntity>();
        }
        roles.add(role);
    }

    //one or zero
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Collection<CompaniesEntity> getCompanies() {
        return companies;
    }

    public void setCompanies(Collection<CompaniesEntity> companies) {
        this.companies = companies;
    }

    @XmlTransient
    @Transient
    public CompaniesEntity getCompany() {
        if(companies!=null && !companies.isEmpty()) {
            return ((ArrayList<CompaniesEntity>) companies).get(0);
        }
        return null;
    }

    public void setCompany(CompaniesEntity company) {
        if(companies==null) {
            companies = new ArrayList<CompaniesEntity>();
        }
        companies.clear();
        companies.add(company);
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Collection<ReservationsEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<ReservationsEntity> reservations) {
        this.reservations = reservations;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "favPK.user")
    public Collection<FavoritesEntity> getFavorites() {
        return favorites;
    }

    public void setFavorites(Collection<FavoritesEntity> favorites) {
        this.favorites = favorites;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
