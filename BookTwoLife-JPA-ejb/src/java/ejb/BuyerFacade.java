/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Buyer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Geraldo
 */
@Stateless
public class BuyerFacade extends AbstractFacade<Buyer> {

    @PersistenceContext(unitName = "BookTwoLife-JPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BuyerFacade() {
        super(Buyer.class);
    }
    
}
