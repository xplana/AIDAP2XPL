/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidap2xpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author wdr
 */
public class NotamsFile {

    ArrayList<Notam> NotamsListe;
    ArrayList<Notam> meinListe;

    File fXmlFile = new File("/Users/wdr/Desktop/notam_I.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    Document doc;

    public NotamsFile() throws IOException, SAXException, ParserConfigurationException {

        // Preparing XML Inport
        this.dBuilder = dbFactory.newDocumentBuilder();
        this.doc = dBuilder.parse(fXmlFile);
        System.out.println("...\r\nProcessind NOTAMs import from: " + fXmlFile);
        doc.getDocumentElement().normalize();

        // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("notam-rec");
        System.out.println("Total Notams found: " + nList.getLength());

        // Create navaid objects
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Notam notam = new Notam();

            Node nNode = nList.item(temp);
            Element eElement = (Element) nNode;

            //Einlesen der einzelnen Elemente des NOTAMS
            notam.setNotam_id(eElement.getElementsByTagName("notam_id").item(0).getTextContent());
            notam.setCns_location_id(eElement.getElementsByTagName("cns_location_id").item(0).getTextContent());
            //notam.setNotam_effective_dtg(eElement.getElementsByTagName("notam_effective_dtg").item(0).getTextContent());
            //notam.setIcao_name(eElement.getElementsByTagName("icao_name").item(0).getTextContent());
            notam.setNotam_text(eElement.getElementsByTagName("notam_text").item(0).getTextContent());

            //System.out.println (temp + ".............................................................");
            //System.out.println("Notam Numer: " + notam.getNotam_id() + " CNS Location: (" + notam.getCns_location_id() + ")\r\n" + notam.getNotam_text());
        }

    }
    
    public Notam identifyPossibleNavaids(String QCode) {
     
        System.out.println("...\r\nLooking for NOTAMSs containing the QCode: " + QCode);
        Notam newNotam = new Notam();
        for(Notam newNotam2:meinListe) {
//            if (newNotam2.getNotam_text().contains(QCode)) {
//            
//        }
        }
       
        return newNotam;
    }
 
}
