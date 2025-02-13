/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Buyer;
import entities.Conversation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Geraldo
 */
@Stateless
public class ConversationFacade extends AbstractFacade<Conversation> {

    @PersistenceContext(unitName = "BookTwoLife-JPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConversationFacade() {
        super(Conversation.class);
    }

    public void insert(Integer buyer, Integer seller) {
        em.createNativeQuery("INSERT INTO CONVERSATION (ID_SELLER,ID_BUYER) VALUES (?,?)")
                .setParameter(1, seller)
                .setParameter(2, buyer)
                .executeUpdate();
    }

    public Boolean existsBetween(Buyer buyer, Integer seller) {
        List resultList = em.createQuery("SELECT c FROM Conversation c WHERE c.idBuyer=:buyer AND c.idSeller.id=:seller")
                .setParameter("seller", seller)
                .setParameter("buyer", buyer)
                .getResultList();
        return !resultList.isEmpty();
    }

    public List<Conversation> findWhereBuyer(Buyer buyer) {
        return em.createQuery("SELECT c FROM Conversation c WHERE c.idBuyer=:buyer")
                .setParameter("buyer", buyer)
                .getResultList();

    }

    public Integer getIdConversation(Integer id, Integer idSeller) {
        return (Integer) em.createQuery("SELECT c.id FROM Conversation c WHERE c.idBuyer.id=:idBuyer AND c.idSeller.id=:idSeller")
                .setParameter("idBuyer", id)
                .setParameter("idSeller", idSeller)
                .getSingleResult();
    }
}
