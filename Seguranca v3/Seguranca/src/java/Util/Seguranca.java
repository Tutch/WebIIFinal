/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Sergio Marinho
 */
public class Seguranca {
    public static String trata(String a){
        if(a==null){
            return "";
        }
        String nova = a.replace("<script>","");
        nova=nova.replace("</script>","");
        nova=nova.replace("<","");
        nova=nova.replace(">","");
        nova=nova.replace("--","");
        return nova;
    }
}
