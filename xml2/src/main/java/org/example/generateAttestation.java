package org.example;

import org.apache.fop.apps.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;

import static java.nio.file.Files.newOutputStream;

public class generateAttestation {
    public final static  String RESOURCES_DIR;
    public final static  String OUTPUT_DIR;
    public final static  String DATA_DIR;


    static {
        RESOURCES_DIR = "src//main//resources//output//";
        OUTPUT_DIR = "src//main//resources//output//Attestation";
        DATA_DIR = "src//main//resources";

    }

    public static void main( String[] args )
    {
        try {
            generateAttestation("18003773");
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

    public static void generateAttestation(String codeI ) throws IOException, SAXException, TransformerException, ParserConfigurationException, XPathExpressionException {
//        boolean result = checkStudentExistence.check(codeI);
//        if (result){
         LocalDate currentDate = LocalDate.now();

            // the XSL FO file
            File xsltFile = new File(DATA_DIR + "//Attestation.xsl");
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
                transformer.setParameter("date", currentDate);

                Result res = new SAXResult(fop.getDefaultHandler());
                transformer.transform(xmlSource, res);
                System.out.println("L'Attestastion est bien generee en succees ! ");
                System.out.println("(veillez chequer /resources/Output/Attestation/"+codeI+")");
            } finally {
                out.close();
            }
        }
    }

