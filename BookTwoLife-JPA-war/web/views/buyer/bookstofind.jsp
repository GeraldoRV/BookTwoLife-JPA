<%-- 
    Document   : bookstofind
    Created on : 05-abr-2019, 16:33:32
    Author     : Geraldo
--%>


<%@page import="entities.Book"%>
<%@page import="java.util.List"%>

<%@page import="javax.naming.InitialContext"%>
<%@page import="ejb.BookFacade"%>
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
            BookFacade bf = InitialContext.doLookup("java:global/BookTwoLife-JPA/BookTwoLife-JPA-ejb/BookFacade!ejb.BookFacade");
            String name = (String) session.getAttribute("bookstofind");
            Integer index = (Integer) session.getAttribute("indextofind");
            if (name == null) {
                name = (String) session.getAttribute("bookstofindcriteria");
            }
            List<Book> books = bf.findByName(name, index);
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
                <%}
                %>

            </ul>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous"><form action="/BookTwoLife-JPA-war/FrontController">
                                <input type="hidden" name="command" value="PreResultsCommand">

                                <button class="page-link" type="submit" style="padding: 0;
                                        border: none;
                                        background-color: inherit;"><span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span></button>
                            </form> 

                        </a>
                    </li>
                    <%
                        Long countByName = (Long) session.getAttribute("indexes");
                        Integer i = 1;
                        while (countByName >=0) {
                    %>



                    <li class="page-item"><a class="page-link" href="#">
                            <form action="/BookTwoLife-JPA-war/FrontController">
                                <input type="hidden" name="command" value="SelectRangeCommand">
                                <input type="hidden" name="npage" value="<%=i%>">
                                <button class="page-link"type="submit"style="padding: 0;
                                        border: none;
                                        background-color: inherit;"><%=i%></button>
                            </form></a></li>


                    <%
                            i++;
                            countByName--;
                        }
                    %>
                   
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next"><form action="/BookTwoLife-JPA-war/FrontController">
                                <input type="hidden" name="command" value="NextResultsCommand">

                                <button class="page-link" type="submit" style="padding: 0;
                                        border: none;
                                        background-color: inherit;"><span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span></button>
                            </form> 

                        </a>
                    </li>
                </ul>
            </nav>


        </div>
    </body>
</html>
