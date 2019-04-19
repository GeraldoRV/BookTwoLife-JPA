

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../../partials/resource.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Paquete</title>
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
                        <label for="nbooks">Cantidad de libros</label>
                        <input type="text" name="nbooks" class="form-control" id="nbooks" placeholder="Cantidad">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="price">Precio</label>
                        <input type="text" name="price" class="form-control" id="price" placeholder="Precio">
                    </div>
                </div>
                <div class="form-group">
                    <label for="details">Detalles</label>
                    <textarea class="form-control" name="details" id="details" placeholder="Ponga los nombres de los libros u oferta.." ></textarea>
                </div>
                <input type="hidden" name="command" value="CreatePackCommand">
                <button type="submit" class="btn btn-warning">Nuevo Paquete</button>
            </form>
        </div>
    </body>
</html>
