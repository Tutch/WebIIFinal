<%-- 
    Document   : cadastroProduto
    Created on : Jun 25, 2016, 7:56:36 PM
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
        <title>Cadastrar Produto</title>
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700,300' rel='stylesheet' type='text/css'></link>
    </head>
    <body>
        <form action="cadastrarProduto" method="post" accept-charset="utf-8">
                <div>
                    <input type="text" name="nome" id="vNome" placeholder="Nome">
                </div>
                <div>
                    <input type="text" name="descricao" id="vDescricao" placeholder="Descrição">
                </div>
                <br>
                <input type="submit" name="cadastrar" value="Cadastrar" id="login">
	</form>
    </body>
</html>
