<%-- 
    Document   : reservaNueva
    Created on : 14 nov. 2021, 14:34:46
    Author     : Lucio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/resources/css/paginaPrincipal.css">
        <link rel="shortcut icon" href="/resources/css/favicon.png">
        <title>Nuevo Vehiculo</title>
    </head>
    <body>
        <h1 id="paginaNuevoVehiculo">Nuevo Vehiculo</h1>

        <form class="planillaDeDatos" action="/cargarVehiculoNuevo" method="POST" autocomplete="off">
            <p>
                Patente:        
                <input id="patente" name="patente" type="text" required=""/>
                <br><br>
                Marca:           
                <input id="marca" name="marca" type="text" required=""/>
                <br><br>            
                Modelo:       
                <input id="modelo" name="modelo" type="number" required=""/>
                <br><br>
                Color:       
                <input id="color" name="color" type="text" required=""/>
                <br><br>
                Tanque:       
                <input id="tanque" name="tanque" type="number" required=""/>     
                <br><br>
                Precio:       
                <input id="precio" name="precio" type="number" required=""/>     
                <br><br>
                
                <button id="guardar" name="guardar" type="submit">Cargar Vehiculo</button>
            </p>
        </form>


        <form class="planillaDeDatos" method="get" action="/cancelarNuevo">
            <button type="submit">Cancelar</button>
        </form> 
    </body>
</html>
