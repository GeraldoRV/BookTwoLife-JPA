/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Geraldo
 */
@Entity
@Table(name = "SELLER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seller.findAll", query = "SELECT s FROM Seller s")
    , @NamedQuery(name = "Seller.findById", query = "SELECT s FROM Seller s WHERE s.id = :id")
    , @NamedQuery(name = "Seller.findByFname", query = "SELECT s FROM Seller s WHERE s.fname = :fname")
    , @NamedQuery(name = "Seller.findByUsername", query = "SELECT s FROM Seller s WHERE s.username = :username")
    , @NamedQuery(name = "Seller.findByPassword", query = "SELECT s FROM Seller s WHERE s.password = :password")})
public class Seller extends UserApp {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeller")
    private List<Valoration> valorationList;
    @OneToMany(mappedBy = "idSeller")
    private List<Pack> packList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeller")
    private List<Conversation> conversationList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeller")
    private List<Book> bookList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeller")
    private List<Solicitude> solicitudeList;

    public Seller() {
    }

    @XmlTransient
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @XmlTransient
    public List<Solicitude> getSolicitudeList() {
        return solicitudeList;
    }

    public void setSolicitudeList(List<Solicitude> solicitudeList) {
        this.solicitudeList = solicitudeList;
    }

    
    public String toXML(){
         String result="";
        try {
           
            JAXBContext jax = JAXBContext.newInstance(Seller.class);
            Marshaller m = jax.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            m.marshal(this, stringWriter);
            result = stringWriter.toString();

        } catch (JAXBException e) {
            Logger.getLogger(Seller.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @XmlTransient
    public List<Valoration> getValorationList() {
        return valorationList;
    }

    public void setValorationList(List<Valoration> valorationList) {
        this.valorationList = valorationList;
    }

    @XmlTransient
    public List<Pack> getPackList() {
        return packList;
    }

    public void setPackList(List<Pack> packList) {
        this.packList = packList;
    }

    @XmlTransient
    public List<Conversation> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<Conversation> conversationList) {
        this.conversationList = conversationList;
    }

    
}
