<%-- 
    Document   : buscarProduto
    Created on : Jun 25, 2016, 11:01:47 PM
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
        <title>Buscar Produto</title>
        <%if(request.getAttribute("msg")!=null){%>
        <script>alert("<%=request.getAttribute("msg")%>");</script>
        <%}%>
    </head>
    <body>
        <form action="buscarProdutoServlet" method="post" accept-charset="utf-8">
            <div id="formLogin">
                <input type="text" name="id" id="vNome" placeholder="ID">
                <br>
                <input type="submit" name="buscar" value="Buscar" id="login">
            </div>
	</form>
        <form action="buscarProdutosServlet" method="post" accept-charset="utf-8">
            <div id="formLogin">
                <input type="submit" name="ver todos os produtos" value="Ver todos os produtos" id="login">
            </div>
	</form>
        
    </body>
</html>
