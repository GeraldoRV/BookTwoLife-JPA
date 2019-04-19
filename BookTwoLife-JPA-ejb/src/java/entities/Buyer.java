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
public class Buyer extends UserApp  {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBuyer")
    private List<Valoration> valorationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBuyer")
    private List<Conversation> conversationList;

    @OneToMany(mappedBy = "idUser")
    private List<Wishlist> wishlistList;

    @Embedded
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBuyer")
    private List<Solicitude> solicitudeList;
    @OneToMany(mappedBy = "idUser")
    private List<Cart> cartList;

    public Buyer() {
    }

    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
   
    @XmlTransient
    public List<Wishlist> getWishlistList() {
        return wishlistList;
    }

    public void setWishlistList(List<Wishlist> wishlistList) {
        this.wishlistList = wishlistList;
    }

  
  
    @XmlTransient
    public List<Valoration> getValorationList() {
        return valorationList;
    }

    public void setValorationList(List<Valoration> valorationList) {
        this.valorationList = valorationList;
    }

    @XmlTransient
    public List<Conversation> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<Conversation> conversationList) {
        this.conversationList = conversationList;
    }

   
   
}
