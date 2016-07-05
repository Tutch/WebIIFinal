<%-- 
    Document   : cadastrarUsuario
    Created on : Jun 26, 2016, 12:10:43 AM
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
        <title>Cadastrar Usuario</title>
    </head>
    <body>
        <form action="cadastrarUsuarioServlet" method="post" accept-charset="utf-8">
            <div id="formLogin">
                <input type="text" name="nome" id="vNome" placeholder="Nome">
                <input type="password" name="password" id="vDescricao" placeholder="password">
                <br>
                <input type="submit" name="cadastrar" value="Cadastrar" id="login">
            </div>
	</form>
    </body>
</html>
