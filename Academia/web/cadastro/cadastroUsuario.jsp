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
            <%if(request.getAttribute("msg")!=null){%>
                alert("<%=request.getAttribute("msg")%>");    
            <%}%>
            function validaForm(){
                var nome = document.forms["form"]["nome"].value;
                var cpf = document.forms["form"]["cpf"].value;
                var endereco = document.forms["form"]["endereco"].value;
                var email = document.forms["form"]["email"].value;
                var password = document.forms["form"]["password"].value;
                var confirma = document.forms["form"]["confirma"].value;
                var diaNasc = document.forms["form"]["diaNasc"].value;
                var mesNasc = document.forms["form"]["mesNasc"].value;
                var anoNasc = document.forms["form"]["anoNasc"].value;
                var sexo = document.forms["form"]["sexo"].value;
                if (nome==null || nome.trim()=="") {
                    alert("Insira um nome válido!");
                    return false;
                }
                if (cpf==null || cpf.trim()=="" || cpf.length<11) {
                    alert("Insira um CPF válido!");
                    return false;
                }
                if (endereco==null || endereco.trim()=="") {
                    alert("Insira um endereço válido!");
                    return false;
                }
                if (endereco==null || endereco.trim()=="") {
                    alert("Insira um endereço válido!");
                    return false;
                }
                if (email==null || email.trim()=="" || email.indexOf("@")<0 || email.indexOf(".com")<0) {
                    alert("Insira um email válido!");
                    return false;
                }
                if (password==null || password.trim()=="" || confirma==null || confirma.trim()=="" || password.length<6) {
                    alert("Insira uma senha válida\nLembre-se: a senha deve ter no mínimo 6 caracteres!");
                    return false;
                }
                if(confirma!=password){
                    alert("Senha e confirmação de senha não coincidem!");
                    return false;
                }
                if (sexo==null || sexo.trim()=="" || sexo.length>1) {
                    alert("Insira um sexo válido!");
                    return false;
                }
                if (diaNasc==null || diaNasc.trim()=="" || diaNasc.length>2 || Number(diaNasc.trim()>31) || Number(diaNasc.trim()<1)) {
                    alert("Insira um dia válido!");
                    return false;
                }
                if (mesNasc==null || mesNasc.trim()=="" || mesNasc.length>2 || Number(mesNasc.trim()>12) || Number(mesNasc.trim()<1)) {
                    alert("Insira um mês válido!");
                    return false;
                }
                if (anoNasc==null || anoNasc.trim()=="" || anoNasc.length>4 || Number(anoNasc.trim()>2015) || Number(anoNasc.trim()<1900)){
                    alert("Insira um ano válido!");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <s:form name="form" action="verificaLogin" onsubmit="return validaForm()">
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
