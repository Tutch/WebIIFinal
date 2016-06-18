/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Entidades.Professor;
import xml.XMLProfessor;




/**
 *
 * @author Mapurunga
 */
public class XMLProfessorParser {
    
    // Não faz sentido retornar a senha do professor no xml
    public static Professor parseXMLparaProfessor(XMLProfessor xml){
        Professor professor = new Professor();
        
        professor.setCodigo(xml.getCodigo());
        professor.setNome(xml.getNome());
        professor.setCpf(xml.getCpf());
        professor.setEmail(xml.getEmail());
        
        return professor;
    }
    
    // Não faz sentido passar a senha do professor no xml
    public static XMLProfessor parseProfessorparaXML(Professor professor){
        XMLProfessor xml = new XMLProfessor();
        
        xml.setCodigo(professor.getCodigo());
        xml.setNome(professor.getNome());
        xml.setCpf(professor.getCpf());
        xml.setEmail(professor.getEmail());
        
        return xml;
    }
    
}
