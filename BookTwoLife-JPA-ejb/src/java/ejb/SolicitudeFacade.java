/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Solicitude;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Geraldo
 */
@Stateless
public class SolicitudeFacade extends AbstractFacade<Solicitude> {

    @PersistenceContext(unitName = "BookTwoLife-JPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudeFacade() {
        super(Solicitude.class);
    }

    public List<Solicitude> findAllByUser(Integer id) {
        return em.createQuery("SELECT s FROM Solicitude s WHERE s.idBuyer.id =:id")
                .setParameter("id", id)
                .getResultList();
    }

    public void insert(Object idbuyer, Object idseller) {
        em.createNativeQuery("INSERT INTO SOLICITUDE (ID_SELLER, ID_BUYER, STATUS) VALUES (?,?,?)")
                .setParameter(1, idseller)
                .setParameter(2, idbuyer)
                .setParameter(3, "En proceso")
                .executeUpdate();

    }
}
