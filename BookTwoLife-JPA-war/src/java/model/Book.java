package model;

import java.io.StringWriter;
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
@XmlType(propOrder = {"bookid","name", "description", "genre", "sellerName", "price"})
public class Book {

    private String name;
    private String description;
    private String genre;
    private String sellerName;
    private String bookid;


    private float price;

    public Book() {
    }

    public Book(String name, String description, String genre, float price) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.price = price;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String toXML() {
        StringWriter result = new StringWriter();
        try {
            JAXBContext jax = JAXBContext.newInstance(Book.class);
            Marshaller m = jax.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(this, sw);
            
            result = sw;
        } catch (JAXBException e) {
            System.out.println(e.getErrorCode());
        }
        return result.toString();
    }

    public Book find(String name) {
        Book book = new Book();
        switch (name) {
            case "Quijote":
                book=new Book("Don Quijote", "En un lugar...", "Lírico", 3.f);
                book.bookid ="quijote";
                book.sellerName="Laura";
                break;
            case "1":
                book= new Book("Don Quijote", "En un lugar...", "Lírico", 3.f);
                break;
            case "Sombras":
                book = new Book("50 Sombras de Gray", "Para mayores de 18", "Erótico", 3.f);
                book.bookid ="sombras";
                book.sellerName="Marco";
                break;
            case "3":
                book= new Book("50 Sombras de Gray", "Para mayores de 18", "Erótico", 3.f);
                break;
            case "2":
                book = new Book("El Principito", "El principito es un cuento poético que viene acompañado\n"
                        + " de...", "Lirico", 3.0f);
                
                break;
            case "Principito":
                book = new Book("El Principito", "El principito es un cuento poético que viene acompañado\n"
                        + " de...", "Lirico", 3.0f);
                book.bookid ="princ";
                book.sellerName="Juan";
            break;    
            default:
                book = new Book("El Principito", "El principito es un cuento poético que viene acompañado\n"
                        + " de...", "Lirico", 3.0f);
                    
        }
        return book;

    }
}
