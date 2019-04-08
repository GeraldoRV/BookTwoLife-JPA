<%-- 
    Document   : main
    Created on : 02-abr-2019, 10:14:49
    Author     : Geraldo
--%>

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
        <div class="container py-2">
            <form action="/BookTwoLife-JPA-war/FrontController">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="name-book">Nombre</label>
                        <input type="text" name="name" class="form-control" id="name-book" placeholder="Nombre">

                    </div>
                    <div class="form-group col-md-3">
                        <label for="genre">Genero</label>
                        <input type="text" name="genre" class="form-control" id="genre" placeholder="Genero">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="price">Precio</label>
                        <input type="text" name="price" class="form-control" id="price" placeholder="Precio">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description">Descripción</label>
                    <textarea class="form-control" name="description" id="description" placeholder="Descripcion..." ></textarea>
                </div>
                <input type="hidden" name="command" value="CreateBookCommand">
                <button type="submit" class="btn btn-primary">Nuevo libro</button>
            </form>
        </div>
    </body>
</html>
