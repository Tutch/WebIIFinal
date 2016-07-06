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
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700,300' rel='stylesheet' type='text/css'></link>
    </head>
    <body>
        <div id="menuInicial">
            <p>Items</p>
            <a class ="button" href ="CadastroProduto">Cadastrar Produto</a>
            <a class ="button" href ="DeletarProduto">Deletar Produto</a>
            <a class ="button" href ="AtualizarProduto">Atualizar Produto</a>
            <a class ="button" href ="BuscarProduto">Buscar Produto</a>

            <p>Usuário</p>
            <a class ="button" href ="CadastrarUsuario">Cadastrar Usuario</a>
            <a class ="button" href ="DeletarUsuario">Deletar Usuario</a>
            <a class ="button" href ="AtualizarUsuario">Atualizar Usuario</a>
            <form action="Logout" method="post" accept-charset="utf-8">
                    <input type="submit" name="cadastrar" value="Sair" id="login">
            </form>
        </div>
    </body>
</html>
