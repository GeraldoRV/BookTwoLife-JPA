/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Geraldo
 */
@Entity
@Table(name = "BUYER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buyer.findAll", query = "SELECT b FROM Buyer b")
    , @NamedQuery(name = "Buyer.findById", query = "SELECT b FROM Buyer b WHERE b.id = :id")
    , @NamedQuery(name = "Buyer.findByFname", query = "SELECT b FROM Buyer b WHERE b.fname = :fname")
    , @NamedQuery(name = "Buyer.findByUsername", query = "SELECT b FROM Buyer b WHERE b.username = :username")
    , @NamedQuery(name = "Buyer.findByPassword", query = "SELECT b FROM Buyer b WHERE b.password = :password")})
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FNAME")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBuyer")
    private Collection<Solicitude> solicitudeCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "buyer")
    private Cart cart;

    public Buyer() {
    }

    public Buyer(Integer id) {
        this.id = id;
    }

    public Buyer(Integer id, String fname, String username) {
        this.id = id;
        this.fname = fname;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Solicitude> getSolicitudeCollection() {
        return solicitudeCollection;
    }

    public void setSolicitudeCollection(Collection<Solicitude> solicitudeCollection) {
        this.solicitudeCollection = solicitudeCollection;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
        if (!(object instanceof Buyer)) {
            return false;
        }
        Buyer other = (Buyer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Buyer[ id=" + id + " ]";
    }
    
}
