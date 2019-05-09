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
import entities.UserApp;
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
public class LogoutCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            UserApp user = (UserApp) session.getAttribute("userlogin");
            
            if (user instanceof Buyer) {
                deleteCart(user);
            }
            
            session.removeAttribute("userlogin");
            forward("/index.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(LogoutCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteCart(UserApp user) {
        try {
            Buyer buyer = (Buyer) user;
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            CartFacade cf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/CartFacade!ejb.CartFacade");
            Cart cart = cf.findWhereBuyer(buyer);
            bf.removeAllFromCart(cart);
            cf.deleteWhereBuyer(buyer);
        } catch (NamingException ex) {
            Logger.getLogger(LogoutCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
