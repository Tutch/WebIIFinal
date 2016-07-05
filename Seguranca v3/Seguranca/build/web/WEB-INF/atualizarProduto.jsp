<%-- 
    Document   : atulizarProduto
    Created on : Jun 25, 2016, 11:01:38 PM
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
        <title>Atualizar Produto</title>
    </head>
    <body>
        <form action="atualizarProdutoServlet" method="post" accept-charset="utf-8">
            <div id="formLogin">
                <input type ="text" name ="id" id="vId" placeholder="ID">
                <input type="text" name="nome" id="vNome" placeholder="Nome">
                <input type="text" name="descricao" id="vDescricao" placeholder="descricao">
                <br>
                <input type="submit" name="cadastrar" value="Atualizar" id="login">
            </div>
	</form>
    </body>
</html>
