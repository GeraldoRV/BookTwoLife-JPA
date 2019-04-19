/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.BuyerFacade;
import ejb.CartFacade;
import ejb.SellerFacade;
import ejb.UserAppFacade;
import ejb.WishlistFacade;
import entities.Buyer;
import entities.Cart;
import entities.Seller;
import entities.UserApp;
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
public class LoginCommand extends FrontCommand {

    private String username;
    private String password;
    private UserApp login;

    @Override
    public void process() {
        username = request.getParameter("username");
        password = request.getParameter("password");
        login();
       
    }
    private void login() {
        try {
            UserAppFacade uf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/UserAppFacade!ejb.UserAppFacade");
            login = uf.login(username, password);
            if (login == null) {
                goTo("index");
            }
            goTo("main");
        } catch (NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveInSession(Object user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("userlogin", user);

    }

    private void setCart(Buyer buyer) {
        try {

            CartFacade cf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/CartFacade!ejb.CartFacade");
            if (!cf.haveACart(buyer)) {

                Cart cart = new Cart();
                cart.setIdUser(buyer);
                cart.setFullPrice(0.);

                cf.create(cart);
            }

        } catch (NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setWishList(Buyer buyer) {
        try {
            WishlistFacade wf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/WishlistFacade!ejb.WishlistFacade");
            if (!wf.haveWisList(buyer)) {
                Wishlist wishlist = new Wishlist();
                wishlist.setIdUser(buyer);
                wf.create(wishlist);

            }
        } catch (NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void goTo(String navigation) {
        switch (navigation) {
            case "index":
                navigate("/index.jsp");
                break;
            case "main":
                if (login instanceof Seller) {
                    saveInSession(login);
                    navigate("/views/seller/main.jsp");
                }
                Buyer buyer = (Buyer) login;
                setCart(buyer);
                setWishList(buyer);
                saveInSession(buyer);
                navigate("/views/buyer/main.jsp");
                break;
            default:
                throw new AssertionError();
        }
    }

    private void navigate(String target) {
        try {
            forward(target);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
