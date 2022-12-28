package org.example;

import org.apache.fop.apps.FOPException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SAXException, IOException, TransformerException, ParserConfigurationException {
        Scanner cardId = new Scanner(System.in);
        System.out.print("Entrer le code appogee de l'etudiant : ");
        int codeA = cardId.nextInt();

        PdfGenerationDemo.convertToPDF(codeA);
    }}