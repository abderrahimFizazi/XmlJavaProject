package org.example;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Veuillez choisir un nombre afin de generer: ");
        System.out.println("1- un fichier XML ");
        System.out.println("2- une carte d'etudiant");
        System.out.println("3- une attestaion");
        System.out.println("4- emploi du temps ");
        System.out.println("5- Releve de notes ");
        Scanner choix = new Scanner(System.in);
        int choi = choix.nextInt();

        System.out.println("Vous avez choisi : "+ choi);
        switch (choi){
            case 1:
                generateXmlFromExcel.main();
                break;
            case 2:
                Scanner cardId = new Scanner(System.in);
                System.out.print("Entrer le code d'identite d'etudiant : ");
                String codeI = cardId.nextLine();
                generateCardID.generateCardID(codeI);
                break;
            case 3:
                Scanner etudId = new Scanner(System.in);
                System.out.print("Entrer le code d'identite d'etudiant : ");
                String etud = etudId.nextLine();
                generateAttestation.generateAttestation(etud);
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Choi invalide!");
                break;
        }

    }}