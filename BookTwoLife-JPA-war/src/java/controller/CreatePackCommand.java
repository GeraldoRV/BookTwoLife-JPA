/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

    private HttpSession session;

    @Override
    public void process() {
        session = request.getSession();
        Seller seller = (Seller) session.getAttribute("userlogin");

        try {
            PackFacade pf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/PackFacade!ejb.PackFacade");
            Pack pack = createEntity(seller);
            pf.create(pack);
        } catch (NamingException ex) {
            Logger.getLogger(CreatePackCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Pack createEntity(Seller seller) {

        String name = request.getParameter("name");
        String nBooks = request.getParameter("nbooks");
        String price = request.getParameter("price");
        String details = request.getParameter("details");
        
        Pack pack = new Pack();
        pack.setIdSeller(seller);
        pack.setDetails(details);
        pack.setFullPrice(Double.parseDouble(price));
        pack.setPname(name);
        pack.setTotalBooks(Integer.parseInt(nBooks));
        return pack;

    }
}
