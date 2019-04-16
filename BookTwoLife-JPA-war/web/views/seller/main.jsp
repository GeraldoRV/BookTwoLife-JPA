<%-- 
    Document   : main
    Created on : 02-abr-2019, 10:14:49
    Author     : Geraldo
--%>

<%@page import="entities.Seller"%>
<%@page import="java.util.List"%>
<%@page import="entities.Book"%>
<%@page import="ejb.BookFacade"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../../partials/resource.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="partials/navbar.jsp" />
        <%
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            Seller seller = (Seller) session.getAttribute("userlogin");
            List<Book> books = bf.findWhereSeller(seller);
        %>
        <div class="container py-2">
            <table class="table mx-auto">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Genero</th>
                        <th>Precio</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Book book : books) {

                    %>
                    <tr>
                        <td><%=book.getBname()%></td>
                        <td><%=book.getGenre()%></td>
                        <td><%=book.getPrice()%></td>
                        <td><form action="/BookTwoLife-JPA-war/FrontController">                   
                                <input type="hidden" name="command" value="DeleteBookCommand">
                                <input type="hidden" name="name" value="Principito">
                                <input type="hidden" name="id" value="<%=Integer.toString(book.getId())%>">
                                <button type="submit" class="btn btn-warning">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>


        
    </div>
</body>
</html>
