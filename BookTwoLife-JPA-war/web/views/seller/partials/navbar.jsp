<%-- 
    Document   : navbar
    Created on : 03-abr-2019, 17:38:18
    Author     : Geraldo
--%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#">BookTwoLife</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/BookTwoLife-JPA-war/views/seller/main.jsp">Libros <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/BookTwoLife-JPA-war/views/seller/addbook.jsp">A�adir un libro</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/BookTwoLife-JPA-war/views/seller/addpack.jsp">A�adir un pack</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/BookTwoLife-JPA-war/views/seller/solicitude.jsp">Solicitudes</a>
      </li>
    </ul>
      <form action="/BookTwoLife-JPA-war/FrontController" class="form-inline mx-auto">
            <input type="hidden" name="command" value="LogoutCommand">
            <button class="btn btn-warning" type="submit">Cerrar sesi�n</button>
        </form>
  </div>
</nav>