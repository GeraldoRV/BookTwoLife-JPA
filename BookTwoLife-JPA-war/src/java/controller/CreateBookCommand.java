/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.BookFacade;
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
public class CreateBookCommand extends FrontCommand{

    @Override
    public void process() {
        HttpSession session = request.getSession();
        Seller seller = (Seller) session.getAttribute("userlogin");
        String name = request.getParameter("name");
        String genre = request.getParameter("genre");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        try {
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            
            bf.createBook(name, description, genre, Double.parseDouble(price), seller.getId()); 
            forward("/views/seller/main.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(CreateBookCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
