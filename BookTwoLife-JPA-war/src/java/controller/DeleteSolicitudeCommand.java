/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.BookFacade;
import ejb.SolicitudeFacade;
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
public class DeleteSolicitudeCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            String id = request.getParameter("id");
            Integer idSolicitude = Integer.parseInt(id);
            
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            SolicitudeFacade sf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/SolicitudeFacade!ejb.SolicitudeFacade");
            
            bf.removeAllFromSolicitude(idSolicitude);
            sf.delete(idSolicitude);
            forward("/views/seller/solicitude.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(DeleteSolicitudeCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
