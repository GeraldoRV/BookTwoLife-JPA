<%-- 
    Document   : wishlist
    Created on : 16-abr-2019, 17:48:06
    Author     : Geraldo
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Book"%>
<%@page import="ejb.BookFacade"%>
<%@page import="entities.Wishlist"%>
<%@page import="ejb.WishlistFacade"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="entities.Buyer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../partials/resource.jsp" />
        <link rel="stylesheet" href="/BookTwoLife-JPA-war/views/buyer/style/mainstyle.css"/>
        <title>BookTwoLife</title>
    </head>
    <body>
        <jsp:include page="partials/navbar.jsp" />
        <%
            Buyer buyer = (Buyer) session.getAttribute("userlogin");
            WishlistFacade wf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/WishlistFacade!ejb.WishlistFacade");
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            Wishlist wishlist = wf.findWhereBuyer(buyer);
            if (wishlist != null) {
                List<Book> books = bf.findWhereWishList(wishlist);
        %>
        <div class="container py-2">
            <ul class="list-group">
                <%
                for (Book book : books) {
                        
                    
                %>
                <li class="list-group-item group-item">
                    <div class="image"><img src="/BookTwoLife-JPA-war/images/princ.jpg" width="80"  alt="princ"/></div>
                    <div class="data">
                        <h5><%=book.getBname()%></h5> 
                        <p><%=book.getDescription()%></p>
                        <small><%=book.getPrice()%> €</small>

                    </div>
                    <div class="button">
                        <form action="/BookTwoLife-JPA-war/FrontController">                   
                            <input type="hidden" name="command" value="TransformViewCommand">
                            <input type="hidden" name="name" value="Principito">
                            <input type="hidden" name="id" value="<%=Integer.toString(book.getId())%>">
                            <button type="submit" class="btn btn-outline-warning">Ver detalles</button>
                        </form>
                        <form action="/BookTwoLife-JPA-war/FrontController">                   
                            <input type="hidden" name="command" value="AddToCartCommand">
                            <input type="hidden" name="name" value="Principito">
                            <input type="hidden" name="id" value="<%=Integer.toString(book.getId())%>">
                            <input type="hidden" name="price" value="<%=Double.toString(book.getPrice())%>">
                            <button type="submit" class="btn btn-warning">Añadir al carrito</button>
                        </form>
                    </div>
                </li>
                <%}%>
                
            </ul> 
            <%}%>
        </div>

    </body>
</html>
