/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Buyer;
import entities.Cart;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Geraldo
 */
@Stateless
public class CartFacade extends AbstractFacade<Cart> {

    @PersistenceContext(unitName = "BookTwoLife-JPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartFacade() {
        super(Cart.class);
    }

    public void updateFullPrice(Double price, Integer idCart) {
        em.createQuery("UPDATE Cart c SET c.fullPrice = c.fullPrice + :price WHERE c.id = :id")
                .setParameter("price", price)
                .setParameter("id", idCart).executeUpdate();
    }

    public void deleteWhereBuyer(Buyer buyer) {
        em.createQuery("DELETE FROM Cart c WHERE c.idUser=:cart")
                .setParameter("cart", buyer)
                .executeUpdate();
    }

    public Cart findWhereBuyer(Buyer buyer) {
        return (Cart) em.createQuery("SELECT c FROM Cart c WHERE c.idUser=:iduser")
                .setParameter("iduser", buyer)
                .getSingleResult();
    }

    public Boolean haveACart(Buyer buyer) {
        List resultList = em.createQuery("SELECT c FROM Cart c WHERE c.idUser=:iduser")
                .setParameter("iduser", buyer)
                .getResultList();
        return !resultList.isEmpty();
    }

}
