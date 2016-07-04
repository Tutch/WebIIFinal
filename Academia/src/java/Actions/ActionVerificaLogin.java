/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Sergio Marinho
 */
public class ActionVerificaLogin extends ActionSupport{
    public String nome,cpf,email,password,confirma,endereco,diaNasc,mesNasc,anoNasc,sexo,msg;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getConfirma() {
        return confirma;
    }

    public void setConfirma(String confirma) {
        this.confirma = confirma;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDiaNasc() {
        return diaNasc;
    }

    public void setDiaNasc(String diaNasc) {
        this.diaNasc = diaNasc;
    }

    public String getMesNasc() {
        return mesNasc;
    }

    public void setMesNasc(String mesNasc) {
        this.mesNasc = mesNasc;
    }

    public String getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(String anoNasc) {
        this.anoNasc = anoNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String retiraXSSeSQLIA(String a){
        String b = a.replace("<script>", "");
        b=b.replace("</script>", "");
        b=b.replace("<", "");
        b=b.replace(">", "");
        return retiraSQLIA(b);
    }
    public String retiraSQLIA(String a){
        String b = a.replace("--", "");
        return b;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    public String execute() throws Exception{
        nome = retiraXSSeSQLIA(nome);
        endereco = retiraSQLIA(endereco);
        try{URL url = new URL("http://localhost:8080/CadastroForaDeSessaoAcademia-war/webresources/cadastrousuario/cadastrar");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String urlParameters  = "nome="+nome+"&cpf="+cpf+"&endereco="+endereco+"&password="+password+"&sexo="+sexo+"&nascimento="+diaNasc+mesNasc+anoNasc+"&email="+email;
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write( postData );
            }catch(Exception e){
                setMsg("Erro ao cadastrar Usuário!");
                return "erro";
            }
            conn.connect();
            if(conn.getResponseCode()!=200){
                setMsg("Erro ao cadastrar Usuário, serviço temporariamente indisponível!");
                return "erro";
            }
            InputStream is = conn.getInputStream();
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data = null;
            String content = "";
            while ((data = reader.readLine()) != null) {
                content += data + "\n";
            }
            if(content.contains("true")){
                setMsg("usuário cadastrado com sucesso!Aguarde a confirmação de um professor!");
                return "sucesso";
            }
            setMsg("Erro ao cadastrar Usuário!");
            return "erro";}catch(Exception e){
                setMsg("Erro ao cadastrar Usuário!");
                return "erro";
            }
    }
}
