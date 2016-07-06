<%-- 
    Document   : atualizarUsuario
    Created on : Jun 26, 2016, 12:10:52 AM
    Author     : Yuri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<!DOCTYPE html>
<% if(request.getSession(false) ==null){
    request.getRequestDispatcher("").forward(request, response);
}%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualizar Usuario</title>
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700,300' rel='stylesheet' type='text/css'></link>
    </head>
    <body>
        <form action="updateUserServlet" method="post" accept-charset="utf-8">
            <div>
                <input type ="text" name ="id" id="vId" placeholder="ID">
            </div>
            <div>
                <input type="text" name="nome" id="vNome" placeholder="Nome">
            </div>
            <div>
                <input type="password" name="password" id="vDescricao" placeholder="password">
            </div>
                <input type="submit" name="cadastrar" value="Atualizar" id="login">
	</form>
    </body>
</html>
