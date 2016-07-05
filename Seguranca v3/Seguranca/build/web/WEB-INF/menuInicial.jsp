<%-- 
    Document   : menuInicial
    Created on : Jun 25, 2016, 10:38:48 PM
    Author     : Yuri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page session="false"%>
<% if(request.getSession(false) ==null){
    request.getRequestDispatcher("").forward(request, response);
}%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="resources/style.css" media="all">
    </head>
    <body>
        <p>Items</p>
        <a class ="button" href ="CadastroProduto">Cadastrar Produto</a><br/>
        <a class ="button" href ="DeletarProduto">Deletar Produto</a><br/>
        <a class ="button" href ="AtualizarProduto">Atualizar Produto</a><br/>
        <a class ="button" href ="BuscarProduto">Buscar Produto</a>
        
        <p>Usuário</p>
        <a class ="button" href ="CadastrarUsuario">Cadastrar Usuario</a><br/>
        <a class ="button" href ="DeletarUsuario">Deletar Usuario</a><br/>
        <a class ="button" href ="AtualizarUsuario">Atualizar Usuario</a><br/>
        
        <form action="Logout" method="post" accept-charset="utf-8">
                <input type="submit" name="cadastrar" value="Sair" id="login">
	</form>
    </body>
</html>
