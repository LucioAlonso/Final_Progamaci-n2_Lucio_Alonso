<%-- 
    Document   : panelVendedor
    Created on : 15 nov. 2021, 1:10:02
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
        <title>Panel de Control</title>
    </head>

    <body>

        <form method="get" id="cerrarSesion" action="/" class="inicioBoton">
            <button type="submit">Cerrar Sesion</button>
        </form>

        <form method="get" id="nuevaReserva" class="inicioBoton" action="/nuevaReservaVendedor">
            <button type="submit">Nueva Reserva</button>
        </form> 


        <h1 class="titulos">PANEL DE CONTROL</h1>
        <h2 class="titulos">Vendedor</h2>



        <form method="post" action="/consultarVendedor" class="consultar">
            <h3 id="consultarReservas">Consultar Reservas</h3>
            <p> 
                DNI:
                <input id="dni_cliente" name="dni_cliente" type="number" /> <button type="submit">Buscar</button>

                <b>
                    <c:forEach var="errores" items="${error}">
                        <p><c:out value="${errores}"/></p>
                    </c:forEach>
                </b>
            </p>     
        </form>     


        <div style = "float: left">
            <h3 class="nombreTabla">Reservas Activas</h3>
            <table border="1" class="tabla">
                <thead>
                    <tr>
                        <th>ID Reserva</th>
                        <th>DNI Cliente</th>
                        <th>Patente</th>
                        <th>Fecha Inicio</th>
                        <th>Precio Total</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach var="reservasAct" items="${listaAct}">
                        <tr>
                            <td><c:out value="${reservasAct.id_reserva}"/></td>
                            <td><c:out value="${reservasAct.dni_cliente}"/></td>
                            <td><c:out value="${reservasAct.patente}"/></td>
                            <td><c:out value="${reservasAct.fecha_inicio}"/></td>
                            <td><c:out value="${reservasAct.precio_total}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div style = "float: left">
            <h3 class="nombreTabla">Reservas Finalizadas</h3>  
            <table border="1" class="tabla"">
                <thead>
                    <tr>
                        <th>ID Reserva</th>
                        <th>DNI Cliente</th>
                        <th>Patente</th>
                        <th>Fecha Inicio</th>
                        <th>Fecha Fin</th>
                        <th>Monto Total</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach var="reservasFin" items="${listaFin}">
                        <tr>
                            <td><c:out value="${reservasFin.id_reserva}"/></td>
                            <td><c:out value="${reservasFin.dni_cliente}"/></td>
                            <td><c:out value="${reservasFin.patente}"/></td>
                            <td><c:out value="${reservasFin.fecha_inicio}"/></td>
                            <td><c:out value="${reservasFin.fecha_fin}"/></td>
                            <td><c:out value="${reservasFin.precio_total}"/></td>                    
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
