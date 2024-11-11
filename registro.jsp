<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>
</head>
<body>
    <h2>Registro de Usuario</h2>

    <form action="register" method="POST">
        <label for="name">Nombre:</label>
        <input type="text" name="name" required><br><br>

        <label for="lastName">Apellido:</label>
        <input type="text" name="lastName" required><br><br>

        <label for="phone">Teléfono:</label>
        <input type="text" name="phone" required><br><br>

        <label for="password">Contraseña:</label>
        <input type="password" name="password" required><br><br>

        <label for="email">Correo electrónico:</label>
        <input type="email" name="email" required><br><br>

        <button type="submit">Registrar</button>
    </form>

    <%-- Mostrar un mensaje de error si ocurrió un problema --%>
    <%
        String error = request.getParameter("error");
        if (error != null && error.equals("1")) {
    %>
        <p style="color: red;">Hubo un error al registrar el usuario. Intenta nuevamente.</p>
    <% } %>
</body>
</html>
