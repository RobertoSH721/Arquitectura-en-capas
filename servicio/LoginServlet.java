package com.gosama.servicio;

import com.gosama.entidades.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

private UsuarioService usuarioService;

@Override
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

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Redirigir al formulario de login (login.jsp)
    request.getRequestDispatcher("/login.jsp").forward(request, response);
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtener los parámetros del formulario de login
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // Llamar al servicio para verificar las credenciales
    Usuario usuario = usuarioService.login(email, password);

    // Verificar si el login fue exitoso
    if (usuario != null) {
        // Si el usuario es válido, redirigir a la página de inicio
        request.getSession().setAttribute("usuario", usuario);  // Guardar al usuario en la sesión
        response.sendRedirect("index.jsp");  // Redirigir a la página principal
    } else {
        // Si las credenciales son incorrectas, redirigir al formulario de login con un mensaje de error
        request.setAttribute("error", "Correo y/o contraseña incorrectos");
        request.getRequestDispatcher("/login.jsp").forward(request, response);  // Redirigir al formulario de login
    }
}

