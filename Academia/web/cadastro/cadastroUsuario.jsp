<%-- 
    Document   : cadastroUsuario
    Created on : 03/07/2016, 14:05:08
    Author     : Sergio Marinho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function validaForm(){
                alert();
                return false;
            }
        </script>
    </head>
    <body>
        <s:form action="verificaLogin" onsubmit="return validaForm()">
            <s:textfield name="nome" label="Nome"/>
            <s:textfield name="cpf" label="CPF" maxlength="11" onkeyup="this.value=this.value.replace(/[^\d]/,'')"/>
            <s:textfield name="endereco" label="Endereço"/>
            <s:textfield name="email" label="Email"/>
            <s:password name="password" label="Senha"/>
            <s:password name="confirma" label="Confirme a Senha"/>
            <s:textfield name="diaNasc" label="Data de Nascimento" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/,'')"/>
            <s:textfield name="mesNasc" label="Mês de Nascimento" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/,'')"/>
            <s:textfield name="anoNasc" label="Ano de Nascimento" maxlength="4" onkeyup="this.value=this.value.replace(/[^\d]/,'')"/>
            <s:radio label="Sexo" name="sexo" list="{'M','F'}" />
            <s:submit value="Cadastrar"/>
        </s:form>
    </body>
</html>
