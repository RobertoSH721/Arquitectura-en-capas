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

        // Verificar si los parámetros para el producto existen (solo cuando se agregan o quitan productos)
        String nombreProducto = request.getParameter("nombreProducto");
        double precioProducto = 0.0;
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
}


