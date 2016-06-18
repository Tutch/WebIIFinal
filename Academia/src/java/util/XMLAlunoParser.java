/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Entidades.Aluno;
import xml.XMLAluno;

/**
 *
 * @author Mapurunga
 */
public class XMLAlunoParser {
    
    // Não faz sentido retornar a senha do aluno no xml
    public static Aluno parseXMLparaAluno(XMLAluno xml){
        Aluno aluno = new Aluno();
        
        aluno.setCodigo(xml.getCodigo());
        aluno.setCpf(xml.getCpf());
        aluno.setAtestado(xml.isAtestado());
        aluno.setEmail(xml.getEmail());
        aluno.setNascimento(xml.getNascimento());
        aluno.setNome(xml.getNome());
        aluno.setSexo(xml.getSexo());
        
        return aluno;
    }
    
    // Não faz sentido passar a senha do aluno no xml
    public static XMLAluno parseAlunoparaXML(Aluno aluno){
        XMLAluno xml = new XMLAluno();
        
        xml.setCodigo(aluno.getCodigo());
        xml.setCpf(aluno.getCpf());
        xml.setAtestado(aluno.isAtestado());
        xml.setEmail(aluno.getEmail());
        xml.setNascimento(aluno.getNascimento());
        xml.setNome(aluno.getNome());
        xml.setSexo(aluno.getSexo());
        
        return xml;
    }
    
}
