<%-- 
    Document   : cart
    Created on : 21-feb-2019, 13:49:33
    Author     : Geraldo
--%>

<%@page import="model.Book"%>
<%@page import="model.Cart"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../partials/resource.jsp" />
        <title>BookTwoLife</title>
    </head>
    <body>
        <jsp:include page="partials/navbar.jsp" />

        <%
            if (session.getAttribute("cart") != null) {
                Cart cart = (Cart) session.getAttribute("cart");
        %>
        <div class="container py-2">
            <form action="/BookTwoLife-JPA-war/FrontController">
                <input type="hidden" name="command" value="SendSolicitudeCommand">
                <button type="submit" class="btn btn-outline-warning">Enviar Solicitud</button>
            </form>
            <ul class="list-group">
                <%
                    for (Book elem : cart.getBooks()) {%>

                <li class="list-group-item justify-content-between align-items-center">
                    <h5><%=elem.getName()%></h5> 
                    <span><%=elem.getDescription()%></span><br>
                    <small><%=elem.getPrice()%> €</small>
                </li>
                <% }

                %>
            </ul> 
            <% } else {
            %> 
            <h1>Carrito Vacío</h1>    
            <%}%>
        </div>

    </body>
</html>
