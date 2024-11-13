<%@ page import="com.gosama.entidades.Usuario" %>
<% 
    // Verifica si el usuario está logueado
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp"); // Redirige a login si no está logueado
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido a tu Perfil</title>
</head>
<body>
    <h1>Bienvenido, ${usuario.nombre} ${usuario.apellidos}</h1>
    
    <p><strong>Nombre:</strong> ${usuario.nombre}</p>
    <p><strong>Apellidos:</strong> ${usuario.apellidos}</p>
    <p><strong>Teléfono:</strong> ${usuario.telefono}</p>
    <p><strong>Correo:</strong> ${usuario.correo}</p>
    
    <!-- Botón para redirigir a la tienda -->
    <form action="tienda.jsp" method="get">
        <button type="submit">Ir a la tienda</button>
    </form>
</body>
</html>
