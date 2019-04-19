/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.BookFacade;
import ejb.PackFacade;
import entities.Pack;
import entities.Seller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geraldo
 */
public class CreatePackCommand extends FrontCommand {

    @Override
    public void process() {
        HttpSession session = request.getSession();
        Seller seller = (Seller) session.getAttribute("userlogin");
        String name = request.getParameter("name");
        String nBooks = request.getParameter("nbooks");
        String price = request.getParameter("price");
        String details = request.getParameter("details");

        try {
            PackFacade pf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/PackFacade!ejb.PackFacade");
            Pack pack = new Pack();
            pack.setIdSeller(seller);
            pack.setDetails(details);
            pack.setFullPrice(Double.parseDouble(price));
            pack.setPname(name);
            pack.setTotalBooks(Integer.parseInt(nBooks));
            pf.create(pack);
        } catch (NamingException ex) {
            Logger.getLogger(CreatePackCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
