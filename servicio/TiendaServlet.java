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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la acción (agregar al carrito, vaciar, etc.)
        String accion = request.getParameter("accion");

        // Obtener el carrito de la sesión
        List<Producto> carrito = (List<Producto>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            request.getSession().setAttribute("carrito", carrito);
        }

        if ("agregar".equals(accion)) {
            // Obtener el nombre y el precio del producto
            String nombreProducto = request.getParameter("nombreProducto");
            double precioProducto = Double.parseDouble(request.getParameter("precioProducto"));

            // Crear el producto y agregarlo al carrito
            Producto producto = new Producto(nombreProducto, precioProducto);
            carrito.add(producto);

            // Actualizar el carrito en la sesión
            request.getSession().setAttribute("carrito", carrito);

        } else if ("vaciarCarrito".equals(accion)) {
            // Vaciar el carrito
            carrito.clear();
            request.getSession().setAttribute("carrito", carrito);
        }

        // Redirigir a la tienda para actualizar la vista
        response.sendRedirect("tienda.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirigir al JSP de la tienda
        request.getRequestDispatcher("/tienda.jsp").forward(request, response);
    }
}
