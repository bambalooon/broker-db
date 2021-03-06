package pl.bb.broker.brokerdb.broker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 02:05
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "role")
@javax.persistence.Table(name = "roles", schema = "public", catalog = "broker")
@Entity
public class RolesEntity {
    @Embeddable
    public static class RolePK implements Serializable {
        @NotNull
        @javax.persistence.Column(name = "role", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
        protected String role;

        @NotNull
        @ManyToOne(fetch = FetchType.LAZY) //no cascade
        @JoinColumn(name = "username")
        protected UsersEntity user;

        public RolePK() {}

        public RolePK(String role, UsersEntity user) {
            this.role = role;
            this.user = user;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RolePK that = (RolePK) o;

            if (role != null ?
                    !(role.equals(that.role)&&user.getUsername().equals(that.user.getUsername()))
                    : that.role != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = role != null ? role.hashCode() : 0;
            result = 31 * result + (user != null && user.getUsername() != null ? user.getUsername().hashCode() : 0);
            return result;
        }

    }

    @EmbeddedId
    private RolePK rolePK = new RolePK();

    @XmlTransient
    public RolePK getRolePK() {
        return rolePK;
    }

    public void setRolePK(RolePK rolePK) {
        this.rolePK = rolePK;
    }

    @XmlElement
    public String getRole() {
        return rolePK.role;
    }

    public void setRole(String role) {
        this.rolePK.role = role;
    }

    @XmlTransient
    public UsersEntity getUser() {
        return rolePK.user;
    }

    public void setUser(UsersEntity user) {
        this.rolePK.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;
        return rolePK.equals(that.getRolePK());
    }

    @Override
    public int hashCode() {
        return rolePK.hashCode();
    }

    @Override
    public String toString() {
        return rolePK.user.getUsername()+": "+rolePK.role;
    }
}
