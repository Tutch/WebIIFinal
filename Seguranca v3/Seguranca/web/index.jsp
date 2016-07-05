<%-- 
    Document   : index
    Created on : Jun 25, 2016, 7:47:54 PM
    Author     : Yuri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page session="false"%>
<% if(request.getSession(false) !=null){
    request.getRequestDispatcher("menuInicial").forward(request, response);
}%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="Autentication" method="post" accept-charset="utf-8">
            <div id="formLogin">
                <input type="text" name="login" id="vNome" placeholder="Usuário">
                <input type="password" name="senha" id="vSenha" placeholder="Senha">
                <br>
                <input type="submit" name="entrar" value="Entrar" id="login">
            </div>
	</form>
    </body>
</html>
