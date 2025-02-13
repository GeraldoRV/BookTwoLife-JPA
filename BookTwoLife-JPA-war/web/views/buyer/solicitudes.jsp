<%-- 
    Document   : solicitudes
    Created on : 25-feb-2019, 10:58:15
    Author     : Geraldo
--%>

<%@page import="ejb.BookFacade"%>
<%@page import="entities.Solicitude"%>
<%@page import="entities.Buyer"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejb.SolicitudeFacade"%>
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
            Buyer buyer = (Buyer) session.getAttribute("userlogin");
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            SolicitudeFacade sf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/SolicitudeFacade!ejb.SolicitudeFacade");
            List<Solicitude> solicitudelist = sf.findAllByUser(buyer.getId());

        %>

        <%if (solicitudelist == null) {%>
        <h1>No hay</h1>
        <%   } else {
        %>

        <div class="container py-2">
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#home">Aprobadas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#menu1">En proceso</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#menu2">Canceladas</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div id="home" class="container tab-pane active"><br>
                    <h3>Aprobadas</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Número</th>
                                <th>Cantidad de libros</th>
                                <th>Anunciante</th>
                                <th>Precio total</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Solicitude elem : solicitudelist) {
                                    if (!elem.getStatus().equals("Compra aprobada")) {
                                        continue;
                                    }
                            Long nBooks=bf.countWhereSolicitude(elem);
                            
                            %>
                            <tr>
                                <td><%=elem.getId()%></td>
                                <td><%=nBooks%></td>
                                <td><%=elem.getIdSeller().getFname()%></td>
                                <td>25€</td>

                                <td>
                                    <form action="/BookTwoLife/FrontController">
                                        <input type="hidden" name="command" value="PayCommand">
                                        <button type="submit" 
                                                class="btn btn-warning">Pagar Compra</button>
                                    </form>
                                <td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
                <div id="menu1" class="container tab-pane fade"><br>
                    <h3>En proceso</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Número</th>
                                <th>Cantidad de libros</th>
                                <th>Anunciante</th>
                                <th>Precio total</th>

                            </tr>
                        </thead>
                        <tbody>
                            <%for (Solicitude elem : solicitudelist) {
                                    if (elem.getStatus().equals("Compra aprobada")) {
                                        continue;
                                    }
                                    Long nBooks = bf.countWhereSolicitude(elem);
                            %>
                            <tr>
                                <td><%=elem.getId()%></td>
                                <td><%=nBooks%></td>
                                <td><%=elem.getIdSeller().getFname()%></td>
                                <td>25€</td>


                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
                <div id="menu2" class="container tab-pane fade"><br>
                    <h3>Canceladas</h3>
                    <span>Ninguna se ha cancelado</span>
                </div>
            </div> 
            <%}%>
        </div>
    </body>
</html>
