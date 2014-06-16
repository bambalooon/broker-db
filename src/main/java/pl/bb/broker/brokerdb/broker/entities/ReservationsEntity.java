package pl.bb.broker.brokerdb.broker.entities;

import org.hibernate.annotations.Cascade;
import pl.bb.broker.brokerdb.broker.xml.SqlDateAdapter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 06.06.14
 * Time: 18:18
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement
@javax.persistence.Table(name = "reservations", schema = "public", catalog = "broker")
@Entity
public class ReservationsEntity {
    private int id;
    private OffersEntity offer;
    private OfferDetailsEntity room;
    private UsersEntity user;
    @NotNull
    private Date arrival;
    @NotNull
    private Date departure;
    private Boolean accepted;

    @XmlAttribute
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @SequenceGenerator(name = "reservations_id_seq", sequenceName = "reservations_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_id_seq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    @XmlJavaTypeAdapter(value = SqlDateAdapter.class)
    @javax.persistence.Column(name = "arrival", nullable = false, insertable = true, updatable = true, length = 13, precision = 0)
    @Basic
    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    @XmlElement
    @XmlJavaTypeAdapter(value = SqlDateAdapter.class)
    @javax.persistence.Column(name = "departure", nullable = false, insertable = true, updatable = true, length = 13, precision = 0)
    @Basic
    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    @XmlElement
    @javax.persistence.Column(name = "accepted", nullable = true, insertable = true, updatable = true)
    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)                //i don't want to change offer, only room
    @JoinColumn(name = "offer", insertable = false, updatable = false)
    public OffersEntity getOffer() {
        return offer;
    }

    public void setOffer(OffersEntity offer) {
        this.offer = offer;
    }

    @XmlElement
    @ManyToOne(fetch = FetchType.EAGER) //no cascade
    @JoinColumns({
            @JoinColumn(name = "offer", referencedColumnName = "offer_id"),
            @JoinColumn(name = "room", referencedColumnName = "room_type")
    })
    public OfferDetailsEntity getRoom() {
        return room;
    }

    public void setRoom(OfferDetailsEntity room) {
        this.room = room;
    }

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationsEntity that = (ReservationsEntity) o;

        if (id != that.id) return false;
        if (arrival != null ? !arrival.equals(that.arrival) : that.arrival != null) return false;
        if (departure != null ? !departure.equals(that.departure) : that.departure != null) return false;
        if (room.getRoomType()!=null ? !room.equals(that.getRoom()) : that.getRoom()!= null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }
}
