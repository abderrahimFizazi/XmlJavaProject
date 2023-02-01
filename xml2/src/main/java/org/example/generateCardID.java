package org.example;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import static java.nio.file.Files.*;

public class generateCardID
{
    public final static  String RESOURCES_DIR;
    public final static  String OUTPUT_DIR;

    static {
        RESOURCES_DIR = "src//main//resources//output//";
        OUTPUT_DIR = "src//main//resources//output//CardId";
    }

    public static void main( String[] args )
    {
        try {
            generateCardID("Ma");
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

    public static void generateCardID(String codeI ) throws IOException, SAXException, TransformerException, ParserConfigurationException, XPathExpressionException {
        boolean result = checkStudentExistence.check(codeI);
        if (result){

        // the XSL FO file
        File xsltFile = new File(RESOURCES_DIR + "//CardId.xsl");
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File(RESOURCES_DIR + "//data.xml"));
        // create an instance of fop factory
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // Setup output
        OutputStream out;
        out = newOutputStream(Paths.get(OUTPUT_DIR + "//" + codeI + ".pdf"));


        try {

            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            transformer.setParameter("code", codeI);
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
            System.out.println("La carte est bien generee en succees ! ");
            System.out.println("(veillez chequer /resources/Output/CardId/"+ codeI + ")");
        } finally {
            out.close();
        }
    }
    }

}
