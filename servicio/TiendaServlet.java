package com.gosama.servicio;

import com.gosama.entidades.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tienda")
public class TiendaServlet extends HttpServlet {

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
        String nombreProducto = request.getParameter("nombreProducto");
        double precioProducto = Double.parseDouble(request.getParameter("precioProducto"));

        if ("agregar".equals(accion)) {
            // Agregar producto al carrito
            Producto producto = new Producto(nombreProducto, precioProducto);
            carrito.add(producto);
        } else if ("quitar".equals(accion)) {
            // Eliminar producto del carrito
            carrito.removeIf(p -> p.getNombre().equals(nombreProducto));
        } else if ("vaciarCarrito".equals(accion)) {
            // Vaciar el carrito
            carrito.clear();
        } else if ("pagar".equals(accion)) {
            // Procesar el pago (esto lo puedes dejar para más adelante)
            // Aquí iría tu lógica de pago, como redirigir a otra página de pago.
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirigir al JSP de la tienda
        request.getRequestDispatcher("/tienda.jsp").forward(request, response);
    }
}
