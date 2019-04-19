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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

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

    public void insert(Object idbuyer, Object idseller) {
        em.createNativeQuery("INSERT INTO SOLICITUDE (ID_SELLER, ID_BUYER, STATUS) VALUES (?,?,?)")
                .setParameter(1, idseller)
                .setParameter(2, idbuyer)
                .setParameter(3, "En proceso")
                .executeUpdate();

    }

    public List<Solicitude> findAllByUser(Integer id) {
        return em.createQuery("SELECT s FROM Solicitude s WHERE s.idBuyer.id =:id")
                .setParameter("id", id)
                .getResultList();
    }

    public List<Solicitude> findAllBySeller(Integer id) {
        return em.createQuery("SELECT s FROM Solicitude s WHERE s.idSeller.id =:id")
                .setParameter("id", id)
                .getResultList();
    }

    public void updateStatus(Integer id, String status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Solicitude> update = cb.createCriteriaUpdate(Solicitude.class);
        Root<Solicitude> solicitude = update.from(Solicitude.class);
        update.set("status", status);
        update.where(cb.equal(solicitude.get("id"), id));
        em.createQuery(update).executeUpdate();
    }

    public void delete(Integer idSolicitude) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Solicitude> delete = cb.createCriteriaDelete(Solicitude.class);
        Root<Solicitude> solicitude = delete.from(Solicitude.class);
        delete.where(cb.equal(solicitude.get("id"), idSolicitude));
        em.createQuery(delete).executeUpdate();
    }

}
