/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.BuyerFacade;
import ejb.CartFacade;
import ejb.SellerFacade;
import entities.Buyer;
import entities.Cart;
import entities.Seller;
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
public class LoginCommand extends FrontCommand {

    private String username;
    private String password;

    @Override
    public void process() {
        username = request.getParameter("username");
        password = request.getParameter("password");

        if (username.equals("buyer")) {
            loginBuyer();
        } else {
            loginSeller();
        }

    }

    private void loginBuyer() {
        Buyer buyer = null;
        BuyerFacade bf;

        try {
            bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BuyerFacade!ejb.BuyerFacade");
            buyer = bf.login(username, password);
            if (buyer != null) {
                if (buyer.getCartList().isEmpty()) {

                    setCart(buyer);
                   
                }
                saveInSession(buyer);

                forward("/views/buyer/main.jsp");
            } else {
                forward("/index.jsp");
            }
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loginSeller() {
        Seller seller = null;
        try {
            SellerFacade sf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/SellerFacade!ejb.SellerFacade");
            seller = sf.login(username, password);
        } catch (NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (seller != null) {
                saveInSession(seller);
                forward("/views/seller/main.jsp");
            } else {
                forward("/index.jsp");
            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveInSession(     Object user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("userlogin", user);

    }

    private void setCart(Buyer buyer) {
        try {
            Cart cart = new Cart();
            cart.setIdUser(buyer);
            cart.setFullPrice(0.);
            CartFacade cf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/CartFacade!ejb.CartFacade");
            cf.create(cart);

        } catch (NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
