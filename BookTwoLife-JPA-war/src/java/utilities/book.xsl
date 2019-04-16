<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl=
    "http://www.w3.org/1999/XSL/Transform" 
    version="1.0"
>

    <xsl:template match="book">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
                      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
                </link>
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" 
                      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
                </link>
                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
                        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
                </script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
                        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous">
                </script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
                        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
                </script>
                <title>BookTwoLife</title>
            </head>
            <body>
                
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
                                <a class="nav-link" href="/BookTwoLife-JPA-war/views/buyer/solicitudes.jsp">Solicitudes</a>
                            </li>
                        </ul>
                        <form action="/BookTwoLife/FrontController" class="form-inline mx-auto mb-0">
                            <input class="form-control mr-sm-2" type="search" placeholder="Busquemos un libro..." aria-label="libro"></input>
                            <input type="hidden" name="command" value="FindBooKCommand"></input>
                            <button class="btn btn-outline-warning my-2 my-sm-0" type="submit">
                                <i class="fas fa-search"></i> Buscar</button>
                        </form>
                    </div>
                </nav>
                <div class="container py-3">
                    <div class="row">
                        <div class="col">
                            <img src="/BookTwoLife-JPA-war/images/princ.jpg" width="100%"  alt="princ"/>
                        </div>
                        <div class="col" >
                            <h1>
                                <xsl:value-of select="bname"/>
                            </h1>
                            <span>
                                <xsl:value-of select="genre"/>
                            </span>
                            <p>
                                <xsl:value-of select="description"/>
                            </p>
                        </div>
                        <div class="col">
                            <h3>
                                <xsl:value-of select="price"/> €
                            </h3>
                            
                            <h4>
                                <xsl:value-of select="idSeller/fname"/>
                            </h4>
                            
                            <form action="/BookTwoLife-JPA-war/FrontController">                   
                                <input type="hidden" name="command" value="TwoStepViewCommand"/>
                                <input type="hidden" name="id" value="{idSeller/id}"/>
                                <button type="submit" class="btn btn-warning">Información del vendedor</button>
                            </form>
                        
                        </div>
                        
                    </div>
                    
                    <a href="/BookTwoLife-JPA-war/views/buyer/main.jsp">Volver</a>
                </div>
            </body>
        </html>
    </xsl:template>
    <xsl:output method="html"/>
</xsl:stylesheet>
