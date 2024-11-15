package com.gosama.servicio;

import com.gosama.entidades.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
*

@author robertosh21
*/
@WebServlet("/registro")
public class UsuarioServlt extends HttpServlet {

private UsuarioService usuarioService;

@OverRide
public void init() throws ServletException {
// Inicializa el servicio de usuarios
try {
this.usuarioService = new UsuarioService();
} catch (SQLException e){
try {
throw new SQLException("No se pudo conectar", e);
} catch (SQLException ex) {
Logger.getLogger(UsuarioServlt.class.getName()).log(Level.SEVERE, null, ex);
}
}

}

@OverRide
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Metodo GET para mostrar el formulario de registro
// Redirigir a la página de registro (formulario)
request.getRequestDispatcher("/registro.jsp").forward(request, response);
}

@OverRide
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Método POST para procesar los datos del formulario de registro
String nombre = request.getParameter("nombre");
String apellidos = request.getParameter("apellidos");
String telefono = request.getParameter("telefono");
String email = request.getParameter("email");
String password = request.getParameter("password");

// Encriptar la contraseña antes de crear el usuario
String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

// Crear un objeto usuario y configurarlo
Usuario usuario = new Usuario();
usuario.setNombre(nombre);
usuario.setApellidos(apellidos);
usuario.setTelefono(telefono);
usuario.setCorreo(email);
usuario.setPassword(hashedPassword); // Usar la contraseña encriptada aquí

// Registrar el usuario
boolean isRegistered = usuarioService.registrarUsuario(usuario);

if (isRegistered) {
response.sendRedirect("index.jsp"); // Redirigir a la página principal después del registro
} else {
response.sendRedirect("registro.jsp?error=1"); // Redirigir al formulario con un error
}
}
}
