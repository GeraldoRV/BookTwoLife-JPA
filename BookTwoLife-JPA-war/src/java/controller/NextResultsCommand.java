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
public class NextResultsCommand extends FrontCommand {

    @Override
    public void process() {
        HttpSession session = request.getSession();
        Integer index = (Integer) session.getAttribute("indextofind");
        Long indexes = (Long) session.getAttribute("indexes");
        index += 3;
        int indexMax = indexes.intValue() * 3;
        System.out.println(indexMax + " max");
        System.out.println(index + "index");
        if (index > indexMax) {
            index = indexMax;
        }
        session.setAttribute("indextofind", index);
        try {
            forward("/views/buyer/bookstofind.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FindBooKCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
