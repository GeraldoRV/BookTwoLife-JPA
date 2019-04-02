/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@XmlType(propOrder = {"sellerName", "bookList"})
public class PageSeller {

    private BookList bookList;
    private String sellerName;

    public PageSeller() {
    }

    public void setBookList(BookList bookList) {
        this.bookList = bookList;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public BookList getBookList() {
        return bookList;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void find(String name) {
        sellerName = name;
        createBook();

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

    private void createBook() {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Book book = new Book();
            book = book.find("" + i);
            bookList.add(book);
        }
        this.bookList = new BookList();
        this.bookList.setBooks(bookList);
    }

}
