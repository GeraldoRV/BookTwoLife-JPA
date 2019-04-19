/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 *
 * @author Geraldo
 */
@Embeddable
public class Address implements Serializable {

    @Size(max = 20)
    @Column(name = "COUNTRY")
    private String country;
    @Size(max = 50)
    @Column(name = "CITY")
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
