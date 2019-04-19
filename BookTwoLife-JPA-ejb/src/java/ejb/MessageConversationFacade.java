/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Conversation;
import entities.MessageConversation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Geraldo
 */
@Stateless
public class MessageConversationFacade extends AbstractFacade<MessageConversation> {

    @PersistenceContext(unitName = "BookTwoLife-JPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageConversationFacade() {
        super(MessageConversation.class);
    }
    public List<MessageConversation> findWhereConver(Conversation conversation){
        return em.createQuery("SELECT m FROM MessageConversation m WHERE m.idConversation=:conver")
                .setParameter("conver", conversation)
                .getResultList();
    }
}
