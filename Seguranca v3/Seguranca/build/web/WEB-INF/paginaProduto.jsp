<%-- 
    Document   : paginaProduto
    Created on : Jun 25, 2016, 11:19:04 PM
    Author     : Yuri
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Classes.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<% if(request.getSession(false) ==null){
    request.getRequestDispatcher("").forward(request, response);
}%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produto</title>
    </head>
    <body>
        <%if(request.getAttribute("itens")==null){%>
        <p>Nome: <%=(String)request.getAttribute("nome")%> </p>
        <p>Descrição: <%=(String)request.getAttribute("description")%> </p>
        <%}else{%>
        <%  
            ArrayList<Item> al = (ArrayList<Item>)request.getAttribute("itens");
            for(Item a:al){%>
               <p>Nome: <%=a.getName()%> </p>
               <p>Descrição: <%=a.getDescription()%> </p><br/>
            <%}
        }%>
    </body>
</html>
