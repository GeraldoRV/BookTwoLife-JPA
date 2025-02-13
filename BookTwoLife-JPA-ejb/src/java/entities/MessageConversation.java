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
@Table(name = "MESSAGE_CONVERSATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageConversation.findAll", query = "SELECT m FROM MessageConversation m")
    , @NamedQuery(name = "MessageConversation.findById", query = "SELECT m FROM MessageConversation m WHERE m.id = :id")
    , @NamedQuery(name = "MessageConversation.findByMessage", query = "SELECT m FROM MessageConversation m WHERE m.message = :message")})
public class MessageConversation implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Size(max = 70)
    @Column(name = "MESSAGE")
    private String message;
    
    @JoinColumn(name = "ID_CONVERSATION", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Conversation idConversation;
    
    @JoinColumn(name = "ID_SELLER", referencedColumnName = "ID")
    @ManyToOne
    private Seller idSeller;
    
    @JoinColumn(name = "ID_BUYER", referencedColumnName = "ID")
    @ManyToOne
    private Buyer idBuyer;

    public MessageConversation() {
    }

    public MessageConversation(Integer id) {
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

    public Conversation getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(Conversation idConversation) {
        this.idConversation = idConversation;
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
        if (!(object instanceof MessageConversation)) {
            return false;
        }
        MessageConversation other = (MessageConversation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MessageConversation[ id=" + id + " ]";
    }

    public Seller getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Seller idSeller) {
        this.idSeller = idSeller;
    }

    public Buyer getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(Buyer idBuyer) {
        this.idBuyer = idBuyer;
    }

}
