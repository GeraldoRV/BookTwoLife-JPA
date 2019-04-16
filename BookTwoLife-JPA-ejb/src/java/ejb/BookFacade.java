/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Book;
import entities.Cart;
import entities.Seller;
import entities.Solicitude;
import entities.Wishlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

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

    public void createBook(String name, String description, String genre, double price, Integer seller) {
        em.createNativeQuery("INSERT INTO BOOK (BNAME,DESCRIPTION,GENRE,PRICE,ID_SELLER) VALUES(?, ?,?,?,?)")
                .setParameter(1, name)
                .setParameter(2, description)
                .setParameter(3, genre)
                .setParameter(4, price)
                .setParameter(5, seller)
                .executeUpdate();
    }

    public void addToCart(Cart idCart, Integer idBook) {
        em.createQuery("UPDATE Book b SET b.idCart =:idCart WHERE b.id =:id")
                .setParameter("idCart", idCart)
                .setParameter("id", idBook)
                .executeUpdate();
    }

    public void addToWishList(Wishlist wishlist, Integer idBook) {
        em.createQuery("UPDATE Book b SET b.idWishlist =:wishlist WHERE b.id =:id")
                .setParameter("wishlist", wishlist)
                .setParameter("id", idBook)
                .executeUpdate();
    }

    public void addToSolicitude(Solicitude idSolicitude, Integer idBook) {
        em.createQuery("UPDATE Book b SET b.idSolicitude =:idSolicitude, b.idCart = null WHERE b.id =:id")
                .setParameter("idSolicitude", idSolicitude)
                .setParameter("id", idBook)
                .executeUpdate();
    }

    public void removeAllFromCart(Cart cart) {
        em.createQuery("UPDATE Book b SET b.idCart =null WHERE b.idCart=:cart")
                .setParameter("cart", cart)
                .executeUpdate();
    }

    public List<Book> findWhereCart(Cart cart) {
        return em.createQuery("SELECT b FROM Book b "
                + "WHERE b.idCart =:cart AND b.idSolicitude IS NULL")
                .setParameter("cart", cart)
                .getResultList();
    }

    public List<Book> findWhereWishList(Wishlist wishlist) {
        return em.createQuery("SELECT b FROM Book b WHERE b.idWishlist=:wishlist AND b.idSolicitude IS NULL")
                .setParameter("wishlist", wishlist)
                .getResultList();
    }

    public List<Book> findWhereSeller(Seller seller) {
        return em.createQuery("SELECT b FROM Book b  WHERE b.idSeller=:seller")
                .setParameter("seller", seller)
                .getResultList();
    }

    public List<Book> findWhereNotCart() {
        return em.createQuery("SELECT b FROM Book b "
                + "WHERE b.idCart IS NULL AND b.idSolicitude IS NULL")
                .getResultList();
    }

    public List<Book> findByName(String name, int index) {

        return em.createQuery("SELECT b FROM Book b "
                + "WHERE b.idCart IS NULL AND b.idSolicitude IS NULL AND b.bname "
                + "LIKE :name ORDER BY b.price")
                .setParameter("name", "%" + name + "%")
                .setFirstResult(index)
                .setMaxResults(3)
                .getResultList();

    }

    public List<Book> findByNameCriteria(String name, int index) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);
        ParameterExpression<String> p = cb.parameter(String.class);

        cq.select(book)
                .where(
                        cb.like(book.get("bname"), p),
                        cb.isNull(book.get("idCart")),
                        cb.isNull(book.get("idSolicitude"))
                )
                .orderBy(cb.asc(book.get("price")));

        TypedQuery<Book> q = em.createQuery(cq);
        q.setParameter(p, "%" + name + "%");
        q.setFirstResult(index);
        q.setMaxResults(3);
        return q.getResultList();
    }

    public void delete(Integer id) {
        em.createQuery("DELETE FROM Book b WHERE b.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Long countByName(String name) {
        return (Long) em.createQuery("SELECT COUNT(b) FROM Book b WHERE b.idCart IS NULL AND b.idSolicitude IS NULL AND b.bname LIKE :name ")
                .setParameter("name", "%" + name + "%")
                .getSingleResult();
    }

    public Long countWhereSolicitude(Solicitude solicitude) {
        return (Long) em.createQuery("SELECT COUNT(b) FROM Book b WHERE b.idSolicitude=:solicitude")
                .setParameter("solicitude", solicitude)
                .getSingleResult();
    }
}
