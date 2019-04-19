/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.ConversationFacade;
import entities.Buyer;
import entities.Conversation;
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
public class OpenConversationCommand extends FrontCommand{

    @Override
    public void process() {
        HttpSession session = request.getSession();
        Buyer buyer = (Buyer) session.getAttribute("userlogin");
               
        try {
            String idSeller = request.getParameter("id");
            ConversationFacade cf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/ConversationFacade!ejb.ConversationFacade");
            if (!cf.existsBetween(buyer, Integer.parseInt(idSeller))) {
                cf.insert(buyer.getId(),Integer.parseInt(idSeller));    
            }
            forward("/views/buyer/chat.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(OpenConversationCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
