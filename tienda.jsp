<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gosama.entidades.Producto" %>

<%
    // Simula productos (en un caso real, los productos se sacarían de la base de datos)
    List<Producto> productos = new ArrayList<>();
    productos.add(new Producto("Producto 1", 10.0));
    productos.add(new Producto("Producto 2", 20.0));
    productos.add(new Producto("Producto 3", 30.0));
    productos.add(new Producto("Producto 4", 40.0));

    // Guarda los productos en la solicitud para que estén disponibles en el JSP
    request.setAttribute("productos", productos);

    // Obtener el carrito de la sesión (si no existe, lo crea)
    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
    if (carrito == null) {
        carrito = new ArrayList<>();
        session.setAttribute("carrito", carrito);
    }

    // Calcular el total
    double total = 0.0;
    for (Producto p : carrito) {
        total += p.getPrecio();
    }
    session.setAttribute("total", total);
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tienda</title>
    <style>
        .producto {
            border: 1px solid #ddd;
            padding: 10px;
            margin: 10px;
            width: 200px;
            text-align: center;
            display: inline-block;
        }
        .carrito {
            margin-top: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #f9f9f9;
            width: 300px;
        }
        .carrito ul {
            list-style-type: none;
            padding: 0;
        }
        .carrito ul li {
            padding: 5px 0;
        }
        .btn {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }
        .btn-danger {
            background-color: #dc3545;
        }
    </style>
</head>
<body>
    <h1>Tienda</h1>
    
    <!-- Mostrar productos disponibles -->
    <h2>Productos disponibles</h2>
    <div class="productos">
        <%
            // Recorrer la lista de productos y mostrar cada uno
            for (Producto producto : productos) {
        %>
        <div class="producto">
            <h3><%= producto.getNombre() %></h3>
            <p>Precio: $<%= producto.getPrecio() %></p>
            <form action="tienda" method="post">
                <input type="hidden" name="nombreProducto" value="<%= producto.getNombre() %>" />
                <input type="hidden" name="precioProducto" value="<%= producto.getPrecio() %>" />
                <button type="submit" name="accion" value="agregar" class="btn">Agregar al carrito</button>
            </form>
        </div>
        <%
            }
        %>
    </div>

    <!-- Mostrar carrito de compras -->
    <h2>Carrito de Compras</h2>
    <div class="carrito">
        <%
            if (carrito.size() > 0) {
        %>
        <ul>
            <%
                // Mostrar los productos del carrito y agregar la opción para quitar un producto
                for (Producto producto : carrito) {
            %>
            <li>
                <%= producto.getNombre() %> - $<%= producto.getPrecio() %>
                <form action="tienda" method="post" style="display:inline;">
                    <input type="hidden" name="nombreProducto" value="<%= producto.getNombre() %>" />
                    <input type="hidden" name="precioProducto" value="<%= producto.getPrecio() %>" />
                    <button type="submit" name="accion" value="quitar" class="btn btn-danger">Quitar</button>
                </form>
            </li>
            <%
                }
            %>
        </ul>
        <p><strong>Total: $<%= total %></strong></p>
        
        <!-- Botón para vaciar el carrito -->
        <form action="tienda" method="post">
            <button type="submit" name="accion" value="vaciarCarrito" class="btn">Vaciar carrito</button>
        </form>

        <!-- Botón para pagar -->
        <form action="tienda" method="post">
            <button type="submit" name="accion" value="pagar" class="btn">Pagar</button>
        </form>
        
        <%
            } else {
        %>
        <p>El carrito está vacío.</p>
        <%
            }
        %>
    </div>

</body>
</html>
