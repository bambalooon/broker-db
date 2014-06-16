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
 * Date: 16.06.14
 * Time: 04:19
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "favorite")
@javax.persistence.Table(name = "favorites", schema = "public", catalog = "broker")
@Entity
public class FavoritesEntity {
    @Embeddable
    public static class FavoritePK implements Serializable {

        @NotNull
        @ManyToOne(fetch = FetchType.LAZY) //no cascade
        @JoinColumn(name = "username", nullable = false)
        protected UsersEntity user;

        @NotNull
        @ManyToOne(fetch = FetchType.LAZY) //no cascade
        @JoinColumn(name = "offer", nullable = false)
        protected OffersEntity offer;

        public FavoritePK() {}

        public FavoritePK(UsersEntity user, OffersEntity offer) {
            this.user = user;
            this.offer = offer;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FavoritePK that = (FavoritePK) o;

            if (offer != null ? !(offer.equals(that.offer)) : that.offer != null) return false;
            if (user != null ? !(user.equals(that.user)) : that.user != null) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = offer != null ? offer.hashCode() : 0;
            result = 31 * result + (user != null ? user.hashCode() : 0);
            return result;
        }

    }

    @EmbeddedId
    private FavoritePK favPK = new FavoritePK();

    @XmlTransient
    public FavoritePK getFavPK() {
        return favPK;
    }

    public void setFavPK(FavoritePK favPK) {
        this.favPK = favPK;
    }

    @XmlTransient
    public UsersEntity getUser() {
        return favPK.user;
    }

    public void setUser(UsersEntity user) {
        this.favPK.user = user;
    }

    @XmlElement
    public OffersEntity getOffer() {
        return favPK.offer;
    }

    public void setOffer(OffersEntity offer) {
        this.favPK.offer = offer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoritesEntity that = (FavoritesEntity) o;
        return favPK.equals(that.getFavPK());
    }

    @Override
    public int hashCode() {
        return favPK.hashCode();
    }
}

