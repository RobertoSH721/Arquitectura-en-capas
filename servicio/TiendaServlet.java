package com.gosama.servicio;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.gosama.entidades.Producto;

@WebServlet("/tienda")
public class TiendaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la sesión
        HttpSession session = request.getSession();

        // Obtener el carrito de la sesión (si no existe, lo crea)
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }

        String accion = request.getParameter("accion");

        // Solo obtenemos los parámetros cuando no se va a vaciar el carrito
        if (!"vaciarCarrito".equals(accion)) {
            String nombreProducto = request.getParameter("nombreProducto");
            double precioProducto = 0.0;

            // Asegurarnos de que el parámetro precioProducto esté presente
            if (request.getParameter("precioProducto") != null) {
                precioProducto = Double.parseDouble(request.getParameter("precioProducto"));
            }

            if ("agregar".equals(accion)) {
                // Agregar producto al carrito
                Producto producto = new Producto(nombreProducto, precioProducto);
                carrito.add(producto);
            } else if ("quitar".equals(accion)) {
                // Eliminar producto del carrito (sin lambda, usando un bucle for)
                for (int i = 0; i < carrito.size(); i++) {
                    Producto p = carrito.get(i);
                    if (p.getNombre().equals(nombreProducto)) {
                        carrito.remove(i);
                        break; // Salir del bucle después de quitar el producto
                    }
                }
            }
        } else {
            // Vaciar el carrito sin procesar los productos
            carrito.clear();
        }

        // Calcular el total
        double total = 0.0;
        for (Producto p : carrito) {
            total += p.getPrecio();
        }
        session.setAttribute("total", total);

        // Redirigir de vuelta a la tienda
        response.sendRedirect("tienda.jsp");
    }
}



