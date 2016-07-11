<%-- 
    Document   : cadastroUsuario
    Created on : 03/07/2016, 14:05:08
    Author     : Sergio Marinho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Aluno</title>
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css"/>
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700,300' rel='stylesheet' type='text/css'></link>
        <script>
            <%if(request.getAttribute("msg")!=null){%>
                alert("<%=request.getAttribute("msg")%>");    
            <%}%>
            function dismiss(){
                var alerta = document.getElementById("alerta");
                alerta.style.display = "none";
            }    
                
            function validaForm(){
                var alerta = document.getElementById("alerta");
                var mensagem = document.getElementById("alertaMensagem");
                
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
                
                if (nome===null || nome.trim()==="") {
                    alerta.style.display = "block";
                    mensagem.innerHTML = "Insira um nome válido!"; 
                    return false;
                }
                if (cpf===null || cpf.trim()==="" || cpf.length<11) {
                    alerta.style.display = "block";
                    mensagem.innerHTML = "Insira um CPF válido!"; 
                    return false;
                }
                if (endereco===null || endereco.trim()==="") {
                    alerta.style.display = "block";
                    mensagem.innerHTML = "Insira um endereço válido!"; 
                    return false;
                }
                if (endereco===null || endereco.trim()==="") {
                    alerta.style.display = "block";
                    mensagem.innerHTML = "Insira um endereço válido!";
                    return false;
                }
                if (email===null || email.trim()==="" || email.indexOf("@")<0 || email.indexOf(".com")<0) {
                    alerta.style.display = "block";
                    mensagem.innerHTML = "Insira um E-mail válido!";
                    return false;
                }
                if (password===null || password.trim()==="" || confirma===null || confirma.trim()==="" || password.length<6) {
                    alerta.style.display = "block";
                    mensagem.innerHTML = "Insira uma senha válida\nLembre-se: a senha deve ter no mínimo 6 caracteres!";
                    return false;
                }
                if(confirma!==password){
                    alerta.style.display = "block";
                    mensagem.innerHTML ="Senha e confirmação de senha não coincidem!";
                    return false;
                }
                if (sexo===null || sexo.trim()==="" || sexo.length>1) {
                    alerta.style.display = "block";
                    mensagem.innerHTML ="Insira um sexo válido!";
                    return false;
                }
                if (diaNasc===null || diaNasc.trim()==="" || diaNasc.length>2 || Number(diaNasc.trim()>31) || Number(diaNasc.trim()<1)) {
                    alerta.style.display = "block";
                    mensagem.innerHTML ="Insira um dia válido!";
                    return false;
                }
                if (mesNasc==null || mesNasc.trim()=="" || mesNasc.length>2 || Number(mesNasc.trim()>12) || Number(mesNasc.trim()<1)) {
                    alert("Insira um mês válido!");
                    return false;
                }
                if (anoNasc===null || anoNasc.trim()==="" || anoNasc.length>4 || Number(anoNasc.trim()>2015) || Number(anoNasc.trim()<1900)){
                    alerta.style.display = "block";
                    mensagem.innerHTML ="Insira um ano válido!";
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <div id="alerta">
            <div>
                <span><p>Alerta</p></span>
                <p id="alertaMensagem"></p>
                <button onclick="return dismiss();">OK</button>
            </div>
        </div>
        
        <s:form name="form" action="verificaLogin" onsubmit="return validaForm()">
            <h2>Menu</h2>
            <div>
                <s:textfield name="nome" placeholder="Nome"/>
            </div> 
            <div>
                <s:textfield name="cpf" placeholder="CPF" maxlength="11" onkeyup="this.value=this.value.replace(/[^\d]/,'')"/>
            </div>
            <div>
                <s:textfield name="endereco" placeholder="Endereço"/>
            </div> 
            <div>
                <s:textfield name="email" placeholder="Email"/>
            </div> 
            <div>
                <s:password name="password" placeholder="Senha"/>
            </div> 
            <div>
                <s:password name="confirma" placeholder="Confirme a Senha"/>
            </div> 
            <div>
                <s:textfield name="diaNasc" placeholder="Data de Nascimento" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/,'')"/>
            </div> 
            <div>
                <s:textfield name="mesNasc" placeholder="Mês de Nascimento" maxlength="2" onkeyup="this.value=this.value.replace(/[^\d]/,'')"/>
            </div> 
            <div>
                <s:textfield name="anoNasc" placeholder="Ano de Nascimento" maxlength="4" onkeyup="this.value=this.value.replace(/[^\d]/,'')"/>
            </div> 
            <span class="label">Sexo:</span>
            <s:radio label="Sexo" name="sexo" list="{'M','F'}" />
            <s:submit class="btnLink" value="Cadastrar"/>
        </s:form>
    </body>
</html>
