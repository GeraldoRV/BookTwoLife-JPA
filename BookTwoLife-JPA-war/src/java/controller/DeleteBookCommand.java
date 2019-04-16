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

/**
 *
 * @author Geraldo
 */
public class DeleteBookCommand extends FrontCommand{

    @Override
    public void process() {
        try {
            String idBook = request.getParameter("id");
           
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            bf.delete(Integer.parseInt(idBook));
            forward("/views/seller/main.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(DeleteBookCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
