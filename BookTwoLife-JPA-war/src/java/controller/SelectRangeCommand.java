/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geraldo
 */
public class SelectRangeCommand extends FrontCommand {

    @Override
    public void process() {
        HttpSession session = request.getSession();
        String npage = request.getParameter("npage");
        Integer nPage = Integer.parseInt(npage);
        session.setAttribute("indextofind", (nPage-1)*3);
        try {
            forward("/views/buyer/bookstofind.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FindBooKCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
