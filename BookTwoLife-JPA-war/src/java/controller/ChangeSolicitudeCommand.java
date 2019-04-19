/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class ChangeSolicitudeCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            
            SolicitudeFacade sf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/SolicitudeFacade!ejb.SolicitudeFacade");
            sf.updateStatus(Integer.parseInt(id), status);
            
            forward("/views/seller/solicitude.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ChangeSolicitudeCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
