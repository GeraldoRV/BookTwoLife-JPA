/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Seller;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Geraldo
 */
@Stateless
public class SellerFacade extends AbstractFacade<Seller> {

    @PersistenceContext(unitName = "BookTwoLife-JPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SellerFacade() {
        super(Seller.class);
    }
    public Seller login(String username, String password){
        Seller seller = null;
        List<Seller> resultList = em.createQuery("SELECT s FROM Seller s WHERE s.username =:username AND s.password =:password")
                .setParameter("username", username)
                .setParameter("password", password).getResultList();
        System.out.println(resultList.size());
        if (!resultList.isEmpty()) {
            seller = resultList.get(0);
        }
        return seller;
    }
    
    
}
