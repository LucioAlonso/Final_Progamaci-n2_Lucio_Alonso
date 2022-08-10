<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/resources/css/paginaPrincipal.css">
        <link rel="shortcut icon" href="/resources/css/favicon.png">
        <title>Inicio Sesion</title>
    </head>
    <body>
        <h1>Bienvenido!</h1>
        <form method="post" action="/ingresar" >
            <p> 
                Usuario:
                <input id="usr" name="usr" type="text" required=""/> 
                <br><br>
                Contraseña:
                <input id="contra" name="contra" type="password" required=""/>  
            </p>

            <b class="mensajeError">
                <c:forEach var="errores" items="${error}">
                    <p class="mensajeError"><c:out value="${errores}"/></p>
                </c:forEach>
            </b>

            <button type="submit">Iniciar Sesion</button>
        </form> 
    </body>
</html>
