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

    File fXmlFile = new File(System.getProperty("user.home") + "/Desktop/notam_I.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    Document doc;

    //Import the NOTAMS file
    public NotamsFile() throws IOException, SAXException, ParserConfigurationException {

        NotamsListe = new ArrayList<>();

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

            // Import NOTAM ID
            try {
                notam.setNotam_id(eElement.getElementsByTagName("notam_id").item(0).getTextContent());
            } catch (Exception e) {
                System.out.println(temp + " Missing notam_id for " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent());
            }

            // Import Location_ID from notam, normally this is the Airport ICAO Code 
            notam.setCns_location_id(eElement.getElementsByTagName("cns_location_id").item(0).getTextContent());

            // Import the ICAO ID, normally this is tha airports name
            try {
                notam.setIcao_id(eElement.getElementsByTagName("icao_id").item(0).getTextContent());
            } catch (Exception e) {
                System.out.println(temp + " Missing ICAO Name " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent());
            }

            // Import ICAO Name from Notam
            try {
                notam.setIcao_name(eElement.getElementsByTagName("icao_name").item(0).getTextContent());
            } catch (Exception e) {
                System.out.println(temp + " Missing ICAO name for " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent());
            }

            // Import the NOTAMS effective
            try {
                notam.setNotam_effective_dtg(eElement.getElementsByTagName("notam_effective_dtg").item(0).getTextContent());
            } catch (Exception e) {
                System.out.println(temp + " Missing Notams effective date " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent());
            }

            // Import the NOTAMS last mod
            try {
                notam.setNotam_lastmod_dtg(eElement.getElementsByTagName("notam_lastmod_dtg").item(0).getTextContent());
            } catch (Exception e) {
                System.out.println(temp + " Missing Notams Ladtmod " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent());
            }

            // Import the NOTAM Text
            notam.setNotam_text(eElement.getElementsByTagName("notam_text").item(0).getTextContent());

            //import the NOTAM Message
            notam.setNotam_report(eElement.getElementsByTagName("notam_report").item(0).getTextContent());

            // Put all the shid into the Container
            this.NotamsListe.add(notam);

        }
        System.out.println("Size to parse: " + NotamsListe.size());
    }

    
    
    
    //Identify possible matchings
    public Notam identifyPossibleNavaids(String QCode) {
      
        System.out.println("...\r\nTrying to find NOTAMS that contain Q-Code: " + QCode);
        
        Notam processingNotam = new Notam();
        System.out.println("Size to parse: " + NotamsListe.size());
        
        // Get all matchings based on the QCode
        for (Notam nt2 : NotamsListe) {

            if (nt2.getNotam_text().contains("/" + QCode)) {
                //System.out.println("Matching: " + nt2.getNotam_id()+ " " + nt2.getNotam_report());
                
                 // remove all "'" 
                 if( nt2.getNotam_text().contains("'")) {
                     
                     System.out.println("Founf a ': " + nt2.getNotam_id()+ " " + nt2.getNotam_report());
                 }
            }
        }
        return processingNotam;
    }

}
