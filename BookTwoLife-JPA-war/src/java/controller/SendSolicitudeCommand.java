/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import model.Book;
import model.Cart;
import model.Solicitude;

/**
 *
 * @author Geraldo
 */
public class SendSolicitudeCommand extends FrontCommand {

    private HttpSession session;

    @Override
    public void process() {
        session = request.getSession(true);
        List<Solicitude> solicitudesList = getSolicitudeList();
        addNewSolicitude(solicitudesList);
        saveInSession(solicitudesList);
       
        cleanCart();
        try {
            forward("/views/buyer/cart.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    }

}
