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
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700,300' rel='stylesheet' type='text/css'></link>
    </head>
    <body>
        <form id="mainPageForm" action="Autentication" method="post" accept-charset="utf-8">
            <div id="formLogin">
                <div>
                    <input type="text" name="login" id="vNome" placeholder="Usuário" tabindex="0">
                </div>
                <div>
                    <input type="password" name="senha" id="vSenha" placeholder="Senha">
                </div>
                <br>
                <input type="submit" name="entrar" value="ENTRAR" id="login">
            </div>
	</form>
    </body>
</html>
