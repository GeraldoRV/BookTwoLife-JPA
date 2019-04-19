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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Geraldo
 */
@Entity
@Table(name = "CONVERSATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversation.findAll", query = "SELECT c FROM Conversation c")
    , @NamedQuery(name = "Conversation.findById", query = "SELECT c FROM Conversation c WHERE c.id = :id")})
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConversation")
    private List<MessageConversation> messageConversationList;
    @JoinColumn(name = "ID_BUYER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Buyer idBuyer;
    @JoinColumn(name = "ID_SELLER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Seller idSeller;

    public Conversation() {
    }

    public Conversation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<MessageConversation> getMessageConversationList() {
        return messageConversationList;
    }

    public void setMessageConversationList(List<MessageConversation> messageConversationList) {
        this.messageConversationList = messageConversationList;
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
        if (!(object instanceof Conversation)) {
            return false;
        }
        Conversation other = (Conversation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Conversation[ id=" + id + " ]";
    }
    
}
