/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Entidades.Exercicios;
import xml.XMLExercicios;




/**
 *
 * @author Mapurunga
 */
public class XMLExerciciosParser {
    
    public static Exercicios parseXMLparaFicha(XMLExercicios xml){
        Exercicios exercicios = new Exercicios();
        
        exercicios.setCodigo(xml.getCodigo());
        exercicios.setDescricao(xml.getDescricao());
        exercicios.setMusculo(xml.getMusculo());
        
        return exercicios;
    }
    
    public static XMLExercicios parseXMLparaFicha(Exercicios exercicios){
        XMLExercicios xml = new XMLExercicios();
        
        xml.setCodigo(exercicios.getCodigo());
        xml.setDescricao(exercicios.getDescricao());
        xml.setMusculo(exercicios.getMusculo());
        
        return xml;
    }
    
}
