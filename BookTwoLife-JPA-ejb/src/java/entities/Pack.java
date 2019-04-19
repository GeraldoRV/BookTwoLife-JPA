/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Geraldo
 */
@Entity
@Table(name = "PACK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pack.findAll", query = "SELECT p FROM Pack p")
    , @NamedQuery(name = "Pack.findById", query = "SELECT p FROM Pack p WHERE p.id = :id")
    , @NamedQuery(name = "Pack.findByFullPrice", query = "SELECT p FROM Pack p WHERE p.fullPrice = :fullPrice")})
public class Pack implements Serializable {

    @Column(name = "TOTAL_BOOKS")
    private Integer totalBooks;

    @Size(max = 30)
    @Column(name = "PNAME")
    private String pname;
    @Size(max = 255)
    @Column(name = "DETAILS")
    private String details;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FULL_PRICE")
    private Double fullPrice;
    @JoinColumn(name = "ID_SELLER", referencedColumnName = "ID")
    @ManyToOne
    private Seller idSeller;

    public Pack() {
    }

    public Pack(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Double fullPrice) {
        this.fullPrice = fullPrice;
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
        if (!(object instanceof Pack)) {
            return false;
        }
        Pack other = (Pack) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pack[ id=" + id + " ]";
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(Integer totalBooks) {
        this.totalBooks = totalBooks;
    }
    
}
