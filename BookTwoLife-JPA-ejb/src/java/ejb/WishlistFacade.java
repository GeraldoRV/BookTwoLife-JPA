/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Buyer;
import entities.Wishlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Geraldo
 */
@Stateless
public class WishlistFacade extends AbstractFacade<Wishlist> {

    @PersistenceContext(unitName = "BookTwoLife-JPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WishlistFacade() {
        super(Wishlist.class);
    }

    public Wishlist findWhereBuyer(Buyer buyer) {
        return (Wishlist) em.createQuery("SELECT w FROM Wishlist w WHERE w.idUser=:iduser")
                .setParameter("iduser", buyer)
                .getSingleResult();
    }

    public boolean haveWisList(Buyer buyer) {
        List resultList = em.createQuery("SELECT w FROM Wishlist w WHERE w.idUser=:iduser")
                .setParameter("iduser", buyer)
                .getResultList();
        return !resultList.isEmpty();
    }
    
}
