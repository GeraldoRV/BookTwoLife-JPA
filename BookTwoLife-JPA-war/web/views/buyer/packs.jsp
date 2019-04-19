<%-- 
    Document   : packs
    Created on : 19-abr-2019, 12:40:19
    Author     : Geraldo
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Pack"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejb.PackFacade"%>
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
            PackFacade pf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/PackFacade!ejb.PackFacade");
            List<Pack> packs = pf.findAll();
        %>
        <div class="container py-2">
            <ul class="list-group">
                <%
                    for (Pack pack : packs) {


                %>
                <li class="list-group-item group-item">

                    <div class="data">
                        <h5><%=pack.getPname()%> </h5> 
                        <p><%=pack.getDetails()%></p>
                        <small><%=pack.getFullPrice()%> €</small>

                    </div>
                </li>
                <%}%>

            </ul>
        </div>
    </body>
</html>
