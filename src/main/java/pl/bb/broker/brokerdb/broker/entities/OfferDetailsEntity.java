package pl.bb.broker.brokerdb.broker.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 26.05.14
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "offerdetails", schema = "public", catalog = "broker")
@Entity
public class OfferDetailsEntity implements Serializable {
    @Embeddable
    public static class OfferDetailsPK implements Serializable {
        @NotNull
        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        protected OffersEntity offer;

        @NotNull
        @Size(min = 1, max = 20)  //doesn't work?
        @javax.persistence.Column(name = "room", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
        @Basic
        protected String room;

        public OfferDetailsPK() {}

        public OfferDetailsPK(OffersEntity offer, String room) {
            this.offer = offer;
            this.room = room;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OfferDetailsPK that = (OfferDetailsPK) o;

            if(offer != null ? offer.getId()!=that.offer.getId() : that.offer != null) {
                return false;
            }

            if(room != null ? room.equals(that.room) : that.room != null) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = room != null ? room.hashCode() : 0;
            result = 31 * result + (offer != null && offer.getDescription() != null ? offer.getDescription().hashCode() : 0);
            return result;
        }

    }
    @EmbeddedId
    private OfferDetailsPK offerDetailsPK = new OfferDetailsPK();
    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    public OfferDetailsPK getOfferDetailsPK() {
        return offerDetailsPK;
    }

    public void setOfferDetailsPK(OfferDetailsPK offerDetailsPK) {
        this.offerDetailsPK = offerDetailsPK;
    }

    public OffersEntity getOffer() {
        return offerDetailsPK.offer;
    }

    public void setOffer(OffersEntity offer) {
        this.offerDetailsPK.offer = offer;
    }

    public String getRoom() {
        return offerDetailsPK.room;
    }

    public void setRoom(String room) {
        this.offerDetailsPK.room = room;
    }

    @javax.persistence.Column(name = "price", nullable = false, insertable = true, updatable = true, length = 7, precision = 2)
    @Basic
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferDetailsEntity that = (OfferDetailsEntity) o;
        return offerDetailsPK.equals(that.getOfferDetailsPK());
    }

    @Override
    public int hashCode() {
        return offerDetailsPK.hashCode();
    }
}
