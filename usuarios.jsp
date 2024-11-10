<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Usuarios</title>
</head>
<body>
    <h1>Lista de Usuarios</h1>
    <ul>
        <c:forEach var="usuario" items="${usuarios}">
            <li>${usuario.nombre} ${usuario.apellidos}</li>
        </c:forEach>
    </ul>

    <h2>Registrar Nuevo Usuario</h2>
    <form action="usuarios" method="post">
        Nombre: <input type="text" name="nombre" /><br/>
        Apellidos: <input type="text" name="apellidos" /><br/>
        Número de Teléfono: <input type="text" name="numeroTelefono" /><br/>
        Correo: <input type="email" name="correo" /><br/>
        Contraseña: <input type="password" name="contrasena" /><br/>
        <input type="submit" value="Registrar" />
    </form>
</body>
</html>
