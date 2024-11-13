package com.gosama.servicio;

import com.gosama.entidades.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/pago")
public class PagoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el método de pago seleccionado
        String metodoPago = request.getParameter("metodoPago");
        HttpSession session = request.getSession();

        // Obtener el carrito de la sesión
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

        // Si el carrito está vacío, redirigir al usuario de nuevo a la tienda
        if (carrito == null || carrito.isEmpty()) {
            response.sendRedirect("tienda.jsp");
            return;
        }

        // Calcular el total
        double total = 0.0;
        for (Producto p : carrito) {
            total += p.getPrecio();
        }

        // Lógica para procesar el pago
        String mensajePago = "";
        boolean pagoExitoso = false;

        switch (metodoPago) {
            case "paypal":
                // Aquí iría la lógica para integrar con PayPal, por ahora simulamos el pago.
                mensajePago = "Pago realizado con PayPal.";
                pagoExitoso = true;
                break;
            case "tarjetaCredito":
            case "tarjetaDebito":
                // Simulación de pago con tarjeta
                mensajePago = "Pago realizado con " + (metodoPago.equals("tarjetaCredito") ? "Tarjeta de Crédito" : "Tarjeta de Débito") + ".";
                pagoExitoso = true;
                break;
            default:
                mensajePago = "Método de pago no válido.";
                break;
        }

        // Si el pago es exitoso, vaciar el carrito y mostrar la confirmación
        if (pagoExitoso) {
            // Vaciar el carrito después de la compra
            carrito.clear();
            session.setAttribute("carrito", carrito);
            session.setAttribute("mensajePago", mensajePago);
            session.setAttribute("totalPago", total);

            // Redirigir a la página de confirmación
            response.sendRedirect("pagoConfirmado.jsp");
        } else {
            // Si el pago no fue exitoso, mostrar un error
            request.setAttribute("mensajeError", "Hubo un error en el proceso de pago.");
            request.getRequestDispatcher("pago.jsp").forward(request, response);
        }
    }
}
