<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gosama - Login</title>
    <style>
        body {
            background-color: rgb(22, 96, 96); /* Fondo del body con el color que mencionaste */
            color: white; /* Color del texto */
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background-color: rgba(255, 255, 255, 0.1);
            padding: 30px;
            border-radius: 8px;
            width: 300px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        }

        .login-container h1 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
            color: #f4f4f4;
        }

        .login-container label {
            display: block;
            font-size: 16px;
            margin-bottom: 8px;
        }

        .login-container input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }

        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: rgb(0, 153, 153);
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .login-container button:hover {
            background-color: rgb(0, 128, 128);
        }

        .error-message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h1>Gosama</h1>
        <form action="login" method="POST">
            <label for="email">Correo electrónico</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Iniciar sesión</button>

            <c:if test="${param.error == 1}">
                <div class="error-message">Credenciales incorrectas. Intenta nuevamente.</div>
            </c:if>
        </form>
    </div>

</body>
</html>
