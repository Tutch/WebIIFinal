/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Entidades.Ficha;
import xml.XMLFicha;


/**
 *
 * @author Mapurunga
 */
public class XMLFichaParser {
    
    public static Ficha parseXMLparaFicha(XMLFicha xml){
        Ficha ficha = new Ficha();
        
        ficha.setCodigo(xml.getCodigo());
        ficha.setCodigo_aluno(xml.getCodigo_aluno());
        ficha.setCodigo_professor(xml.getCodigo_professor());
        ficha.setDescricao(xml.getDescricao());
        
        return ficha;
    }
    
    public static XMLFicha parseFichaparaXML(Ficha ficha){
        XMLFicha xml = new XMLFicha();
        
        xml.setCodigo(ficha.getCodigo());
        xml.setCodigo_aluno(ficha.getCodigo_aluno());
        xml.setCodigo_professor(ficha.getCodigo_professor());
        xml.setDescricao(ficha.getDescricao());
        
        return xml;
    }
    
}
