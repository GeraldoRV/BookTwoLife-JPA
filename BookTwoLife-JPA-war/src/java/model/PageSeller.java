/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import ejb.BookFacade;
import entities.Book;
import entities.Seller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Geraldo
 */
@XmlRootElement
@XmlType(propOrder = {"seller", "bookList"})
public class PageSeller {

    private BookList bookList;
    private Seller seller;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
        try {
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            List<Book> books = bf.findWhereSeller(seller);
            bookList.setBooks(books);
        } catch (NamingException ex) {
            Logger.getLogger(PageSeller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PageSeller() {
        bookList = new BookList();
    }

    public void setBookList(BookList bookList) {
        this.bookList = bookList;
    }

   
    public BookList getBookList() {
        return bookList;
    }

   
    public String toXml() {
        String result="";
        try {
           
            JAXBContext jax = JAXBContext.newInstance(PageSeller.class);
            Marshaller m = jax.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            m.marshal(this, stringWriter);
            result = stringWriter.toString();

        } catch (JAXBException e) {
            Logger.getLogger(PageSeller.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    

}
