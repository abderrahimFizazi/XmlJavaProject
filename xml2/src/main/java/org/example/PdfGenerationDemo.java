package org.example;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static java.nio.file.Files.*;

public class PdfGenerationDemo
{
    public final static  String RESOURCES_DIR;
    public final static  String OUTPUT_DIR;

    static {
        RESOURCES_DIR = "src//main//resources//";
        OUTPUT_DIR = "src//main//resources//output//";
    }

    public static void main( String[] args )
    {
        try {
            convertToPDF(1);
        } catch (FOPException | IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertToPDF(int Appoge ) throws IOException, SAXException, TransformerException, ParserConfigurationException, XPathExpressionException {
        DocumentBuilderFactory test = DocumentBuilderFactory.newInstance();
        test.setNamespaceAware(true);
        Document doc = test.newDocumentBuilder().parse(new FileInputStream(RESOURCES_DIR+ "data.xml"));

        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//*[code="+ Appoge+"]";
        boolean result = (boolean) xpath.evaluate(expression, doc, XPathConstants.BOOLEAN);
        if (!result){
            System.out.println("il y a aucun etudiant avec le code Appoge "+Appoge);
        }
        else{

        // the XSL FO file
        File xsltFile = new File(RESOURCES_DIR + "//template.xsl");
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File(RESOURCES_DIR + "//data.xml"));
        // create an instance of fop factory
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // Setup output
        OutputStream out;
        out = newOutputStream(Paths.get(OUTPUT_DIR + "//" + Appoge + ".pdf"));


        try {

            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            transformer.setParameter("code", Appoge);
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
            System.out.println("La carte est bien generee en succees ! ");

        } finally {
            out.close();
        }
    }
    }

}
