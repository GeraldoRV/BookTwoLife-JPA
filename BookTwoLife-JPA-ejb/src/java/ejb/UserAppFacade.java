/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Seller;
import entities.UserApp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Geraldo
 */
@Stateless
public class UserAppFacade extends AbstractFacade<UserApp>{

    @PersistenceContext(unitName = "BookTwoLife-JPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserAppFacade() {
        super(UserApp.class);
    }
    public UserApp login(String username, String password){
        UserApp user = null;
        List<UserApp> resultList = em.createQuery("SELECT u FROM UserApp u WHERE u.username =:username AND u.password =:password")
                .setParameter("username", username)
                .setParameter("password", password).getResultList();
        System.out.println(resultList.size());
        if (!resultList.isEmpty()) {
            user = resultList.get(0);
        }
        return user;
    }
    
}
