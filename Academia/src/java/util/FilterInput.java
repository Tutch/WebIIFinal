/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Mapurunga
 */
public class FilterInput {
   
    // Alerta sobre a existencia de caracteres que podem ser usados
    // para um ataque XSS
    static boolean identifyDangerousCharacters(String conteudo){
        for(int i = 0; i<conteudo.length(); i++){
            char c = conteudo.charAt(i);
            
            if( c == '/' || c == '%' || c == '>'){
                return true;
            }
        }
        
        return false;
    }
    
}
