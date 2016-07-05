<%-- 
    Document   : deletarProduto
    Created on : Jun 25, 2016, 11:01:28 PM
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
        <title>Deletar Produto</title>
    </head>
    <body>
        <form action="deletarProdutoServlet" method="post" accept-charset="utf-8">
            <div id="formLogin">
                <input type="text" name="id" id="vNome" placeholder="ID">
                <br>
                <input type="submit" name="buscar" value="Deletar" id="login">
            </div>
	</form>
    </body>
</html>
