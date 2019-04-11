/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    , @NamedQuery(name = "Buyer.findByPassword", query = "SELECT b FROM Buyer b WHERE b.password = :password")
    , @NamedQuery(name = "Buyer.findByCountry", query = "SELECT b FROM Buyer b WHERE b.address.country = :country")
    , @NamedQuery(name = "Buyer.findByCity", query = "SELECT b FROM Buyer b WHERE b.address.city = :city")})
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
    @Embedded
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBuyer")
    private List<Solicitude> solicitudeList;
    @OneToMany(mappedBy = "idUser")
    private List<Cart> cartList;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
    public List<Solicitude> getSolicitudeList() {
        return solicitudeList;
    }

    public void setSolicitudeList(List<Solicitude> solicitudeList) {
        this.solicitudeList = solicitudeList;
    }

    @XmlTransient
    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
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
