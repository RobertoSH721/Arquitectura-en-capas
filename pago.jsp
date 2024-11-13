<%@ page import="java.util.List" %>
<%@ page import="com.gosama.entidades.Producto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pagar - Tienda</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Resumen de Compra</h1>

    <h3>Productos en el carrito:</h3>
    <table border="1">
        <tr>
            <th>Nombre</th>
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

    <h3>Total: <%= total %> €</h3>

    <h2>Elige un método de pago</h2>
    <form action="pago" method="post">
        <input type="radio" name="metodoPago" value="paypal" id="paypal"> PayPal<br>
        <input type="radio" name="metodoPago" value="tarjetaCredito" id="tarjetaCredito"> Tarjeta de Crédito<br>
        <input type="radio" name="metodoPago" value="tarjetaDebito" id="tarjetaDebito"> Tarjeta de Débito<br><br>

        <input type="submit" value="Realizar Pago">
    </form>
</body>
</html>
