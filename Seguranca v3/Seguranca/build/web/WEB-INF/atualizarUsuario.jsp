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
    </head>
    <body>
        <form action="updateUserServlet" method="post" accept-charset="utf-8">
            <div id="formLogin">
                <input type ="text" name ="id" id="vId" placeholder="ID">
                <input type="text" name="nome" id="vNome" placeholder="Nome">
                <input type="password" name="password" id="vDescricao" placeholder="password">
                <br>
                <input type="submit" name="cadastrar" value="Atualizar" id="login">
            </div>
	</form>
    </body>
</html>
