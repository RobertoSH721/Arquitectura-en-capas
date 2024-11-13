<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gosama - Login</title>
    <style>
        body {
            background-color: rgb(22, 96, 96);
            color: white;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
            width: 300px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin: 10px 0 15px;
            border: none;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: rgb(0, 123, 123);
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: rgb(0, 150, 150);
        }
        .error {
            color: red;
            text-align: center;
        }
        .contenedor { 
        display: flex; 
        justify-content: center; 
        align-items: center; 
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login Gosama</h2>
        <div>
            <image src="imagenes/gosama.png" width="100" heigth="100">
        </div>    
        <form action="login" method="POST">
            <label for="email">Email</label>
            <input type="text" id="email" name="email" required>
            
            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required>
            
            <input type="submit" value="Iniciar sesión">
        </form>
        <c:if test="${not empty error}">
        <div style="color: red;">
            <p>${error}</p>
        </div>
    </c:if>
    </div>
</body>
</html>

