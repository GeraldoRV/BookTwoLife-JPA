<%-- 
    Document   : chat
    Created on : 19-abr-2019, 11:13:58
    Author     : Geraldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!------ Include the above in your HEAD tag ---------->


<html>
    <head>
     <jsp:include page="../../partials/resource.jsp" />
     <link rel="stylesheet" href="/BookTwoLife-JPA-war/views/buyer/style/chatstyle.css"/>
    </head>
    <body>
        <jsp:include page="partials/navbar.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-3"><h2>Conversaciones</h2>
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action active">
                            Cras justo odio
                        </a>
                        <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
                        <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
                        <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
                        <a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">Vestibulum at eros</a>
                    </div>
                </div>
                <div class="col"><h2>Conversacion con</h2></div>
            </div>
        </div>
    </body>
</html>