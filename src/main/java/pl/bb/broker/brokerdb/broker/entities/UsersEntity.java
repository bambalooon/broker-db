package pl.bb.broker.brokerdb.broker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Collection;
import pl.bb.broker.security.settings.SecuritySettings;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 02:05
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "users", schema = "public", catalog = "broker")
@Entity
public class UsersEntity {
    @XmlElement
    @Size(min = SecuritySettings.USERNAME_MIN, max = SecuritySettings.USERNAME_MAX)
    @Pattern(regexp = SecuritySettings.USERNAME_PATTERN)
    @NotNull
    private String username;

    @XmlElement
    @Size(min = SecuritySettings.PASSWORDHASH_LEN, max = SecuritySettings.PASSWORDHASH_LEN)
    @NotNull
    private String password;
    @XmlElement
    private Collection<RolesEntity> roles;
    //No XmlElement
    private Collection<CompaniesEntity> companiesEntities;

    @javax.persistence.Column(name = "username", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @javax.persistence.Column(name = "password", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Collection<CompaniesEntity> getCompaniesEntities() {
        return companiesEntities;
    }

    public void setCompaniesEntities(Collection<CompaniesEntity> companiesEntities) {
        this.companiesEntities = companiesEntities;
    }

    @Transient
    public CompaniesEntity getCompany() {
        if(companiesEntities!=null && !companiesEntities.isEmpty()) {
            return ((ArrayList<CompaniesEntity>) companiesEntities).get(0);
        }
        return null;
    }

    public void setCompany(CompaniesEntity company) {
        if(companiesEntities==null) {
            companiesEntities = new ArrayList<CompaniesEntity>();
        }
        companiesEntities.clear();
        companiesEntities.add(company);
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
