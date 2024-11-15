package com.gosama.servicio;

import com.gosama.datos.DatabaseConnection;
import com.gosama.datos.UsuarioDAO;
import com.gosama.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
*

@author robertosh21
*/
public class UsuarioService {

private Connection connection;
private UsuarioDAO usuarioDAO;

// Constructor que obtiene la conexión a la base de datos
public UsuarioService() throws SQLException {
    try {
        this.connection = DatabaseConnection.getConnection(); // Conexión a la base de datos
        this.usuarioDAO = new UsuarioDAO(); // Inicializamos el DAO
    } catch (SQLException e) {
        // Manejo de la excepción: podemos lanzar una RuntimeException si no podemos obtener la conexión
        throw new SQLException("No se pudo establecer la conexión a la base de datos", e);
    }
}

// Método para registrar al usuario en la base de datos
public boolean registrarUsuario(Usuario usuario) {
    // Consulta SQL para insertar el usuario en la base de datos
    String sql = "INSERT INTO usuario (nombre, apellidos, telefono, email, password) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        // Asignar los parámetros de la consulta SQL
        stmt.setString(1, usuario.getNombre());
        stmt.setString(2, usuario.getApellidos());
        stmt.setString(3, usuario.getTelefono());
        stmt.setString(4, usuario.getCorreo());
        stmt.setString(5, usuario.getPassword());  

        // Ejecutar la consulta de inserción
        int rowsAffected = stmt.executeUpdate();
        
        // Si se insertó al menos un usuario, retorna true
        return rowsAffected > 0;
    } catch (SQLException e) {
        // Si ocurre un error, imprime la excepción en los logs
        e.printStackTrace();
        return false;  // Si hay un error, retorna false
    }
}

    // Método para verificar las credenciales del usuario durante el login
public Usuario login(String email, String password) {
    // Llamamos al método del DAO que se encarga de verificar el login
    return usuarioDAO.verificarLogin(email, password);
}
}
