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
        <link rel="stylesheet" type="text/css" href="/resources/css/paginaPrincipal.css">
        <link rel="shortcut icon" href="/resources/css/favicon.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva Reserva</title>
    </head>
    <body>
        <h1 id="paginaNuevaReserva">Nueva Reserva</h1>

        <form class="planillaDeDatos" action="/agregarReservaVendedor" method="POST" autocomplete="off">
            <p>
                Nombre:        
                <input id="nombre" name="nombre" type="text" required=""/>
                <br><br>
                DNI:           
                <input id="dni" name="dni" type="number" required=""/>
                <br><br>            
                Telefono:       
                <input id="telefono" name="telefono" type="number" required=""/>
                <br><br>
                Direccion:       
                <input id="direccion" name="direccion" type="text" required=""/>
                <br><br>
                
                Vehiculo:  
                <select name="vehiculos" required="">
                    <table>
                        <option value=""disabled selected>--Seleccione un vehiculo--</option>
                        <c:forEach var="vehiculos" items="${lista}">
                            <option value="${vehiculos.patente}" label="<c:out value="${vehiculos.marca}"/> (<c:out value="${vehiculos.patente}"/>) $<c:out value="${vehiculos.precio}"/>"></option>  
                        </c:forEach>
                    </table>
                </select>               
                <br><br>
                
                <button id="guardar" name="guardar" type="submit">Realizar Reserva</button>
            </p>
        </form>


        <form class="planillaDeDatos" method="get" action="/cancelar">
            <button type="submit">Cancelar</button>
        </form> 
        
    </body>
</html>
