/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.BookFacade;
import ejb.CartFacade;
import ejb.WishlistFacade;
import entities.Buyer;
import entities.Wishlist;
import java.io.IOException;
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
public class AddToWishListCommand extends FrontCommand {

    private HttpSession session;

    @Override
    public void process() {
        try {
            session = request.getSession(true);

            String idBook = request.getParameter("id");
            saveInWishList(idBook);
            forward("/views/buyer/main.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(AddToWishListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void saveInWishList(String idBook) throws NamingException {
        Buyer buyer = (Buyer) session.getAttribute("userlogin");
        BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
        WishlistFacade wf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/WishlistFacade!ejb.WishlistFacade");
        Wishlist wishlist = wf.findWhereBuyer(buyer);
        bf.addToWishList(wishlist, Integer.parseInt(idBook));
    }

}
