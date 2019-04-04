/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.BookFacade;
import ejb.CartFacade;
import entities.Buyer;
import entities.Cart;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import model.Book;

/**
 *
 * @author Geraldo
 */
public class AddToCartCommand extends FrontCommand {

    private HttpSession session;

    @Override
    public void process() {
        session = request.getSession(true);

        String idBook = request.getParameter("id");
        saveInCart(idBook);

        try {
            forward("/views/buyer/main.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void saveInCart(String idBook) {
        Buyer buyer = (Buyer) session.getAttribute("userlogin");
        try {
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            Cart cart = buyer.getCart();
            bf.addToCart(cart, Integer.parseInt(idBook));
            updatePrice(cart);
        } catch (NamingException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updatePrice(Cart cart) {
        try {
            CartFacade cf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/CartFacade!ejb.CartFacade");
            String price = request.getParameter("price");
            double priceBook = Double.parseDouble(price);
            cf.updateFullPrice(priceBook, cart.getId());
        } catch (NamingException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
