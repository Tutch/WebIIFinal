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
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700,300' rel='stylesheet' type='text/css'></link>
        <%if(request.getAttribute("msg")!=null){%>
        <script>alert("<%=request.getAttribute("msg")%>");</script>
        <%}%>
    </head>
    <body>
        <form action="buscarProdutoServlet" method="post" accept-charset="utf-8">
            <div>
                <input type="text" name="id" id="vNome" placeholder="ID">
            </div>
            <input type="submit" name="buscar" value="Buscar" id="login">
            <input type="submit" name="ver todos os produtos" value="Ver todos os produtos" id="login">
	</form>
        
    </body>
</html>
