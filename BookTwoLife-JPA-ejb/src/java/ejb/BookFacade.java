/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Book;
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
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "BookTwoLife-JPA-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
    public List<Book> findWhereNotCart(){
       return em.createQuery("SELECT b FROM Book b "
                + "WHERE b.idCart IS NULL AND b.idSolicitude IS NULL")
                .getResultList();
    }
    
    public void addToCart(Cart idCart,Integer idBook){
        em.createQuery("UPDATE Book b SET b.idCart =:idCart WHERE b.id =:id")
                .setParameter("idCart", idCart)
                .setParameter("id", idBook).executeUpdate();
    }
}
