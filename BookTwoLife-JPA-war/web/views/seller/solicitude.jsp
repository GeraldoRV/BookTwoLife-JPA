<%-- 
    Document   : solicitude
    Created on : 19-abr-2019, 18:28:50
    Author     : Geraldo
--%>

<%@page import="entities.Solicitude"%>
<%@page import="java.util.List"%>
<%@page import="ejb.BookFacade"%>
<%@page import="ejb.SolicitudeFacade"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="entities.Seller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../../partials/resource.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitudes</title>
    </head>
    <body>
        <jsp:include page="partials/navbar.jsp" />
        <div class="container py-2">
            <%
                Seller seller = (Seller) session.getAttribute("userlogin");
                BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
                SolicitudeFacade sf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/SolicitudeFacade!ejb.SolicitudeFacade");

                List<Solicitude> solicitudelist = sf.findAllBySeller(seller.getId());
            %>
            <table class="table">
                <thead>
                    <tr>
                        <th>Cliente</th>
                        <th>Estado</th>
                        <th>Cant. Libros</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Solicitude solicitude : solicitudelist) {
                            Long nBooks = bf.countWhereSolicitude(solicitude);


                    %>
                    <tr>
                        <td><%=solicitude.getIdBuyer().getFname()%></td>
                        <td>
                            <form action="/BookTwoLife-JPA-war/FrontController">                   
                                <input type="hidden" name="command" value="ChangeSolicitudeCommand">
                                <select name="status" class="custom-select">
                                    
                                    <option selected><%=solicitude.getStatus()%></option>
                                    <option>Compra aprobada</option>
                                    <option>En proceso</option>
                                
                                    <option>Compra cancelada</option>
                                </select>
                                <input type="hidden" name="id" value="<%=Integer.toString(solicitude.getId())%>">
                                <button type="submit" class="btn btn-warning">Cambiar</button>
                            </form></td>
                        <td><%=nBooks%></td>
                        <td><form action="/BookTwoLife-JPA-war/FrontController">                   
                                <input type="hidden" name="command" value="DeleteSolicitudeCommand">
                                <input type="hidden" name="id" value="<%=Integer.toString(solicitude.getId())%>">
                                <button type="submit" class="btn btn-warning">Eliminar</button>
                            </form></td>

                    </tr>




                    <%}%>
                   
                </tbody>
            </table>




        </div>
    </body>
</html>
