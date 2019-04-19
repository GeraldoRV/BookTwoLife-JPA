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
public class PreResultsCommand extends FrontCommand {

    private HttpSession session;

    @Override
    public void process() {
        session = request.getSession();
        calculatePrevIndex();

        try {
            forward("/views/buyer/bookstofind.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FindBooKCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void calculatePrevIndex() {
        Integer index = (Integer) session.getAttribute("indextofind");

        index -= 3;
        if (index < 0) {
            index = 0;
        }
        session.setAttribute("indextofind", index);
    }

}
