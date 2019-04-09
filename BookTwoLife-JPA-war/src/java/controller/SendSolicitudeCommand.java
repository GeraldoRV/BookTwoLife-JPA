/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.CartFacade;
import ejb.SolicitudeFacade;
import entities.Book;
import entities.Buyer;
import entities.Cart;
import entities.Solicitude;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void process() {
        try {
            session = request.getSession(true);
            Buyer buyer = (Buyer) session.getAttribute("userlogin");
            CartFacade cf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/CartFacade!ejb.CartFacade");

            SolicitudeFacade sf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/SolicitudeFacade!ejb.SolicitudeFacade");

            Cart cart = cf.find(buyer.getCartList().get(0).getId());
            List<Solicitude> solicitudeList = sf.findAllByUser(buyer.getId());
            List<entities.Book> bookList = cart.getBookList();
            for (Book book : bookList) {
                for (Solicitude solicitude : solicitudeList) {
                    if (book.getIdSeller().getId() == solicitude.getIdSeller().getId()) {
                        solicitude.getBookList().add(book);
                    }
                }
            }

            //List<Solicitude> solicitudesList = getSolicitudeList();
            //addNewSolicitude(solicitudesList);
            //saveInSession(solicitudesList);
           // cleanCart();
            try {
                forward("/views/buyer/cart.jsp");
            } catch (ServletException | IOException ex) {
                Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(SendSolicitudeCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    private List<Solicitude> createSolicitudeList() {
        return new ArrayList<>();
    }

    private List<Solicitude> getSolicitudeList() {

        List<Solicitude> solicitudeList;
        if (session.isNew()) {
            solicitudeList = createSolicitudeList();
            createAproveSolitude(solicitudeList);
            session.setAttribute("solicitudeslist", solicitudeList);

        } else {
            if (session.getAttribute("solicitudeslist") == null) {
                solicitudeList = createSolicitudeList();
                createAproveSolitude(solicitudeList);

            } else {
                solicitudeList = (List<Solicitude>) session.getAttribute("solicitudeslist");

            }
        }
        return solicitudeList;
    }

    private void saveInSession(List<Solicitude> solicitudeList) {
        session.setAttribute("solicitudeslist", solicitudeList);
    }

    private void cleanCart() {
        session.setAttribute("cart", null);
    }

    private List<Book> getBooks() {
        Cart cart = (Cart) session.getAttribute("cart");
        return cart.getBooks();
    }

    private void addNewSolicitude(List<Solicitude> solicitudesList) {
        List<Book> books = getBooks();
        Solicitude newSolicitude = new Solicitude(solicitudesList.size() + 1, books, "Solicitud en proceso", "Fernando");
        solicitudesList.add(newSolicitude);
    }

    private void createAproveSolitude(List<Solicitude> solicitudeList) {
        Solicitude solicitude = new Solicitude(1, getBooks(), "Compra aprobada", "Margaret");
        solicitudeList.add(solicitude);
    }*/

}
