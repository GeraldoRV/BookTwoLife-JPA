/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.BookFacade;
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
public class FindBooKCommand extends FrontCommand {

    private HttpSession session;

    @Override
    public void process() {
        String name = request.getParameter("name");
        if (!name.isEmpty()) {
            session = request.getSession();
            session.setAttribute("bookstofind", name);
            Integer index = 0;
            session.setAttribute("indextofind", index);
            calculateNPags(name);
        }
        try {
            forward("/views/buyer/bookstofind.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FindBooKCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void calculateNPags(String name) {
        try {
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            Long countByName = bf.countByName(name);

            if (countByName > 3) {
                countByName /= 3;

            } else {
                countByName = 0L;
            }

            session.setAttribute("indexes", countByName);
        } catch (NamingException ex) {
            Logger.getLogger(FindBooKCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
