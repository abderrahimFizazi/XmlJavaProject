package org.example;

import java.io.FileOutputStream;
 import java.io.OutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

 import javax.xml.stream.XMLOutputFactory;
 import javax.xml.stream.XMLStreamException;
 import javax.xml.stream.XMLStreamWriter;

 public class generateXmlFromExcel {

     public static void main() throws Exception {
         // Open the Excel file
         Workbook workbook = new XSSFWorkbook("src/main/resources/DATA_GINF2.xlsx");

         // Get the first sheet from the workbook
         Sheet sheet = workbook.getSheetAt(0);

         // Create an OutputStream to write the XML file
         OutputStream outputStream = new FileOutputStream("src/main/resources/output/data.xml");

         // Create an XMLStreamWriter to write the XML data
         XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
         XMLStreamWriter writer = outputFactory.createXMLStreamWriter(outputStream);

         // Write the XML declaration and root element
         writer.writeStartDocument();
         writer.writeStartElement("Etudiants");

         // Iterate over the rows in the sheet
         for (Row row : sheet) {
             if(row.getRowNum() == 0)
                 continue;
             // Write a new element for each row
             writer.writeStartElement("Etudiant");

             // Iterate over the cells in the row
             for (Cell cell : row) {
                 // Write an element for each cell, with the cell value as the element content
                 switch(cell.getColumnIndex()) {
                     case 0:
                         String apogee = String.valueOf(cell.toString());
                         writer.writeAttribute("Apogee",apogee);
                         break;
                     case 1:
                         writer.writeStartElement("Nom");
                         writer.writeCharacters(cell.toString());
                         writer.writeEndElement();
                         break;
                     case 2:
                         writer.writeStartElement("Prenom");
                         writer.writeCharacters(cell.toString());
                         writer.writeEndElement();
                         break;
                     case 3:
                         writer.writeStartElement("Email_Instit");
                         writer.writeCharacters(cell.toString());
                         writer.writeEndElement();
                         break;
                     case 4:
                         writer.writeStartElement("Telephone");
                         writer.writeCharacters(cell.toString());
                         writer.writeEndElement();
                         break;
                     case 5:
                         writer.writeStartElement("Date_Naissance");
                         writer.writeCharacters(cell.toString());
                         writer.writeEndElement();
                         break;
                     case 6:
                         writer.writeStartElement("Code_identite");
                         writer.writeCharacters(cell.toString());
                         writer.writeEndElement();
                         break;
                     case 7:
                         writer.writeStartElement("Origine");
                         writer.writeCharacters(cell.toString());
                         writer.writeEndElement();
                         break;
                 }
             }
             // End the row element
             writer.writeEndElement();
         }

         // End the root element and close the XMLStreamWriter
         writer.writeEndElement();
         writer.writeEndDocument();
         writer.close();

         // Close the OutputStream
         outputStream.close();
         System.out.println("Fichier XML est bien genere (veillez chequer /resources/data.xml)");
     }
 }
