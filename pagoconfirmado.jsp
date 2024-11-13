<%@ page import="java.util.List" %>
<%@ page import="com.gosama.entidades.Producto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pago Exitoso</title>
</head>
<body>
    <h1>Pago Exitoso</h1>

    <h3>Resumen de tu compra:</h3>
    <table border="1">
        <tr>
            <th>Nombre del Producto</th>
            <th>Precio</th>
        </tr>
        <%
            List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
            double total = 0.0;
            for (Producto p : carrito) {
                total += p.getPrecio();
        %>
        <tr>
            <td><%= p.getNombre() %></td>
            <td><%= p.getPrecio() %> €</td>
        </tr>
        <% } %>
    </table>

    <h3>Total: <%= session.getAttribute("totalPago") %> €</h3>
    <h4><%= session.getAttribute("mensajePago") %></h4>

    <p>Gracias por tu compra. ¡Tu pedido está en proceso!</p>

    !-- Enlace para volver a la tienda -->
    <a href="tienda.jsp" class="btn">Volver a la tienda</a>
</body>
</html>
