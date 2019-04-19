/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Geraldo
 */
@Entity
@Table(name = "VALORATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valoration.findAll", query = "SELECT v FROM Valoration v")
    , @NamedQuery(name = "Valoration.findById", query = "SELECT v FROM Valoration v WHERE v.id = :id")
    , @NamedQuery(name = "Valoration.findByMessage", query = "SELECT v FROM Valoration v WHERE v.message = :message")
    , @NamedQuery(name = "Valoration.findByStars", query = "SELECT v FROM Valoration v WHERE v.stars = :stars")})
public class Valoration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "STARS")
    private Integer stars;
    @JoinColumn(name = "ID_BUYER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Buyer idBuyer;
    @JoinColumn(name = "ID_SELLER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Seller idSeller;

    public Valoration() {
    }

    public Valoration(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Buyer getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(Buyer idBuyer) {
        this.idBuyer = idBuyer;
    }

    public Seller getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Seller idSeller) {
        this.idSeller = idSeller;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoration)) {
            return false;
        }
        Valoration other = (Valoration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Valoration[ id=" + id + " ]";
    }

}
