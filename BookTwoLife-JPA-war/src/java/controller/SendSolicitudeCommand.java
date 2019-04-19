/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.BookFacade;
import ejb.CartFacade;
import ejb.SolicitudeFacade;
import entities.Book;
import entities.Buyer;
import entities.Cart;
import entities.Solicitude;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geraldo
 */
public class SendSolicitudeCommand extends FrontCommand {

    private HttpSession session;
    private Buyer buyer;
    private SolicitudeFacade sf;
    private BookFacade bf;

    @Override
    public void process() {
        try {
            session = request.getSession(true);
            buyer = (Buyer) session.getAttribute("userlogin");
            CartFacade cf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/CartFacade!ejb.CartFacade");
            bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            sf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/SolicitudeFacade!ejb.SolicitudeFacade");

            Cart cart = cf.findWhereBuyer(buyer);
            List<Book> bookList =bf.findWhereCart(cart);

            for (Book book : bookList) {
                insertInSolicitude(book);
                if (book.getIdSolicitude() == null) {
                    createSolicitude(book);
                    insertInSolicitude(book);

                }
            }
            
            try {
                forward("/views/buyer/cart.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(SendSolicitudeCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertInSolicitude(Book book) throws NamingException {
        sf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/SolicitudeFacade!ejb.SolicitudeFacade");

        List<Solicitude> solicitudeList = sf.findAllByUser(buyer.getId());
        for (Solicitude solicitude : solicitudeList) {
            if (Objects.equals(book.getIdSeller().getId(), solicitude.getIdSeller().getId())) {
                bf.addToSolicitude(solicitude, book.getId());
                book.setIdSolicitude(solicitude);
                break;
            }
        }
    }

    private void createSolicitude(Book book) {
        Solicitude solicitude = new Solicitude();
        solicitude.setIdBuyer(buyer);
        solicitude.setStatus("En proceso");
        solicitude.setIdSeller(book.getIdSeller());
        sf.create(solicitude);
    }

}
