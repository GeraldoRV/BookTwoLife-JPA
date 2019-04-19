<%-- 
    Document   : navbar
    Created on : 21-feb-2019, 13:47:39
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
        <a class="nav-link" href="/BookTwoLife-JPA-war/views/buyer/main.jsp">Inicio <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/BookTwoLife-JPA-war/views/buyer/cart.jsp">Carrito</a>        
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/BookTwoLife-JPA-war/views/buyer/wishlist.jsp">Deseos</a>        
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/BookTwoLife-JPA-war/views/buyer/solicitudes.jsp">Solicitudes</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/BookTwoLife-JPA-war/views/buyer/chat.jsp">Conversaciones</a>
      </li>
    </ul>
      <form action="/BookTwoLife-JPA-war/FrontController" class="form-inline mx-auto">
            <input class="form-control mr-sm-2" name="name" type="search" placeholder="Busquemos un libro..." aria-label="libro">
            <input type="hidden" name="command" value="FindBooKCommand">
            <button class="btn btn-outline-warning my-2 my-sm-0" type="submit"><i class="fas fa-search"></i> Buscar</button>
        </form>
      <form action="/BookTwoLife-JPA-war/FrontController" class="form-inline">
            <input class="form-control mr-sm-2" name="name" type="search" placeholder="Busquemos un libro(C)..." aria-label="libro">
            <input type="hidden" name="command" value="FindBooKCriteriaCommand">
            <button class="btn btn-outline-warning my-2 my-sm-0" type="submit"><i class="fas fa-search"></i> Buscar</button>
        </form>
      <form action="/BookTwoLife-JPA-war/FrontController" class="form-inline mx-auto">
            <input type="hidden" name="command" value="LogoutCommand">
            <button class="btn btn-warning" type="submit">Cerrar sesión</button>
        </form>
  </div>
</nav>