/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.ConversationFacade;
import ejb.MessageConversationFacade;
import entities.Buyer;
import entities.Conversation;
import entities.MessageConversation;
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
public class SendMessageCommand extends FrontCommand {

    private HttpSession session;
    private Buyer buyer;

    @Override
    public void process() {
        try {
            session = request.getSession(true);
            buyer = (Buyer) session.getAttribute("userlogin");
            String message = request.getParameter("message");
            String idConversation = request.getParameter("id");
            createMessage(message, idConversation);

            forward("/views/buyer/chat.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(SendMessageCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createMessage(String message, String idConversation) {
        try {
            ConversationFacade cf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/ConversationFacade!ejb.ConversationFacade");
            Conversation conversation = cf.find(Integer.parseInt(idConversation));
            MessageConversationFacade mcf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/MessageConversationFacade!ejb.MessageConversationFacade");
            MessageConversation messageConver = new MessageConversation();
            messageConver.setIdConversation(conversation);
            messageConver.setMessage(message);
            messageConver.setIdBuyer(buyer);
            mcf.create(messageConver);
        } catch (NamingException ex) {
            Logger.getLogger(SendMessageCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
