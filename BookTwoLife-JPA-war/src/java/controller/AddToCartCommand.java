/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import model.Book;
import model.Cart;

/**
 *
 * @author Geraldo
 */
public class AddToCartCommand extends FrontCommand {

    private HttpSession session;

    @Override
    public void process() {
        session = request.getSession(true);

        String idBook = request.getParameter("name");
        Cart cart = getCart();

        addBookIn(cart, idBook);

        try {
            forward("/views/buyer/main.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Cart getCart() {
        Cart cart;
        if (session.isNew()) {
            cart = createCart();
            session.setAttribute("cart", cart);

        } else {
            if (session.getAttribute("cart") == null) {
                cart = createCart();
            } else {

                cart = (Cart) session.getAttribute("cart");
            }
        }
        return cart;
    }

    private Cart createCart() {
        return new Cart();
    }

    private void saveInSession(Cart cart) {
        session.setAttribute("cart", cart);
    }

    private void addBookIn(Cart cart, String id) {
        if (id != null && !id.isEmpty()) {
            Book book = findBook(id);
            cart.addBook(book);
            saveInSession(cart);
        }
    }

    private Book findBook(String id) {
        Book book = new Book();
        book = book.find(id);
        return book;
    }
}
