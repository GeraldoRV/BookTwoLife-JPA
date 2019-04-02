/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import model.Book;

/**
 *
 * @author Geraldo
 */
public class TransformViewCommand extends FrontCommand {

    private final String PATH ="C:\\Users\\Geraldo.LAPTOP-09QGLT5H\\Documents\\NetBeansProjects\\AS1819\\BookTwoLife-JPA\\BookTwoLife-JPA-war\\src\\java\\utilities";

    @Override
    public void process() {
        Book book = new Book();
        book = book.find(request.getParameter("name"));
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xsl = new StreamSource(new File(PATH + "book.xsl"));
            
            Transformer newTransformer = factory.newTransformer(xsl);
            StreamSource xml = new StreamSource(new StringReader(book.toXML()));
            
            PrintWriter out = response.getWriter();
            Result result = new StreamResult(out);
            newTransformer.transform(xml, result);
           
        } catch (IOException | TransformerException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
