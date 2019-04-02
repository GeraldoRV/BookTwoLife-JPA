<%-- 
    Document   : unknow
    Created on : 21-feb-2019, 13:22:06
    Author     : Geraldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BookTwoLife</title>
    </head>
    <body>
        <h1>Algo ha ido mal! :(</h1>
        <%String url = (String) session.getAttribute("url");%>
        <a href="<%=url%>">Volver</a>
    </body>
</html>
