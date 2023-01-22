package org.example;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SAXException, IOException, TransformerException, ParserConfigurationException, XPathExpressionException {
        Scanner cardId = new Scanner(System.in);
        System.out.print("Entrer le code d'identite d'etudiant : ");
        String codeI = cardId.nextLine();
       generateCardID.generateCardID(codeI);
    }}