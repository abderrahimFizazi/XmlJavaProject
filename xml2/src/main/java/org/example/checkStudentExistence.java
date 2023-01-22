package org.example;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class checkStudentExistence {
   public final static  String RESOURCES_DIR;
    static {
        RESOURCES_DIR = "src//main//resources//output//";
    }
    public static boolean check(String codeI) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory test = DocumentBuilderFactory.newInstance();
        test.setNamespaceAware(true);
        Document doc = test.newDocumentBuilder().parse(new FileInputStream(RESOURCES_DIR + "data.xml"));

        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//*[@Apogee=" + codeI + "]";
        boolean result = (boolean) xpath.evaluate(expression, doc, XPathConstants.BOOLEAN);
        if (!result){
            System.out.println("Il y a aucun etudiant avec le code d'identite "+codeI);
            return false;
        }
        return true;
    }
}
