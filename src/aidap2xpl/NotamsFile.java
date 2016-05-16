/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidap2xpl;

import java.io.IOException;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    debug out = new debug();

    // get all the stored path´s for the files
    Settings set = new Settings();

    ArrayList<Notam> NotamsListe;
    ActionItem output = new ActionItem();
    ActionFile ActionFileForXPlane = new ActionFile();

    // XML Kram
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    Document doc;

    //Import the NOTAMS file
    public NotamsFile() throws IOException, SAXException, ParserConfigurationException {

        NotamsListe = new ArrayList<>();

        // TODO Check XML integrity
        // Preparing XML Inport
        this.dBuilder = dbFactory.newDocumentBuilder();
        this.doc = dBuilder.parse(set.getNotamsXMLFile());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("notam-rec");
        
        //logging
        out.printToScreen("...\r\nProcessind NOTAMs import from: " + set.getNotamsXMLFile(), 1);
        out.printToScreen("Total Notams found: " + nList.getLength(), 1);
        

        // Read XML-File
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Notam notam = new Notam();

            Node nNode = nList.item(temp);
            Element eElement = (Element) nNode;

            // Import NOTAM ID
            try {
                notam.setNotam_id(eElement.getElementsByTagName("notam_id").item(0).getTextContent());
            } catch (Exception e) {
                out.printToScreen(temp + " Missing notam_id for " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent(), 0);
            }

            // Import Location_ID from notam, normally this is the Airport ICAO Code 
            notam.setCns_location_id(eElement.getElementsByTagName("cns_location_id").item(0).getTextContent());

           
            // TODO Import the account ID, looks lik AAHHGS
            
            
            // Import the ICAO ID, normally this is tha airports name
            try {
                notam.setIcao_id(eElement.getElementsByTagName("icao_id").item(0).getTextContent());
            } catch (Exception e) {
                out.printToScreen(temp + " Missing ICAO Name " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent(), 0);
            }

            // Import ICAO Name from Notam
            try {
                notam.setIcao_name(eElement.getElementsByTagName("icao_name").item(0).getTextContent());
            } catch (Exception e) {
                out.printToScreen(temp + " Missing ICAO name for " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent(), 0);
            }

            // Import the NOTAMS effective
            try {
                notam.setNotam_effective_dtg(eElement.getElementsByTagName("notam_effective_dtg").item(0).getTextContent());
            } catch (Exception e) {
                out.printToScreen(temp + " Missing Notams effective date " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent(), 0);
            }

            // Import the NOTAMS last mod
            try {
                notam.setNotam_lastmod_dtg(eElement.getElementsByTagName("notam_lastmod_dtg").item(0).getTextContent());
            } catch (Exception e) {
                out.printToScreen(temp + " Missing Notams Ladtmod " + eElement.getElementsByTagName("cns_location_id").item(0).getTextContent(), 0);
            }

            // Import the NOTAM Text
            notam.setNotam_text(eElement.getElementsByTagName("notam_text").item(0).getTextContent());

            //import the NOTAM Report
            notam.setNotam_report(eElement.getElementsByTagName("notam_report").item(0).getTextContent());
            
            // TODO import NOTAM_NRC -> looks like NOTAMR, NOTAMN..
            
           

            // Put all the stuff into the Container
            this.NotamsListe.add(notam);

        }
        System.out.println("Total Number of Notams found: " + NotamsListe.size());
    }

    //Identify possible Navaids
    public Notam identifyPossibleNavaids(String QCode) throws IOException {

        
        Notam processingNotam = new Notam();

        System.out.println("...\r\nTrying to find NOTAMS that contain Q-Code: " + QCode);
        System.out.println("Size to parse: " + NotamsListe.size());

        for (Notam nt2 : NotamsListe) {

            // Get all matchings based on the QCode
            if (nt2.getNotam_text().contains("/" + QCode)) {
                //System.out.println("\r\nORIGINAL:...................");
                //System.out.println(nt2.getNotam_text());

                // Check if line E) is availabile in NOTAM TEXT
                if (nt2.getNotam_text().contains("E) ")) {

                    // Remove unwanted artifacts and expressions
                    String cleanLineE = nt2.
                            getNotam_text().replaceAll("'", "")
                            .replaceAll("POSSIBLE FALSE INDICATIONS", "")
                            .replaceAll("DO NOT USE", "")
                            .replaceAll("DUE TO MAINTENANCE", "")
                            .replaceAll("POSSIBLE FALSE INDICATION", "")
                            .replaceAll("OUT OF SERVICE", "")
                            .replaceAll("U/S", "")
                            .replaceAll("\\?", "")
                            .replaceAll("/", "")
                            .replaceAll("VOR", "")
                            .replaceAll("DME", "")
                            .replaceAll("CH1", "")
                            .replaceAll("UNSERVICEABLE", "");

                    output.setItemMessage(cleanLineE);

                    // trying to identify the Navaids name
                    // Step1. load notam_report and do some cleaning
                    String cleanReport = nt2.getNotam_report().
                            replaceAll("'", "")
                            .replaceAll("\\.", "")
                            .replaceAll("\\?", "")
                            .replaceAll("/", "")
                            .replaceAll("\\(", "")
                            .replaceAll("\\)", "")
                            .replaceAll("VOR", "")
                            .replaceAll("NDB", "")
                            .replaceAll("DME", "")
                            .replaceAll("VUB", "");

                    // Step2. split the Report in single strings
                    String[] cleanReportSplitted = cleanReport.split(" ");

                    // trying to identify the navaids frequency    
                    Pattern p = null;

                    // Parse for Navaid type VOR/DME    
                    if (QCode.startsWith("QNM")) {
                        //determine type of Navaid
                        output.setItemType("VOR/DME");

                        //define regexpattern for this kind of navaid
                        p = Pattern.compile(
                                //definition VOR 108.00 - 117.95
                                "[1]{1}[0-1]{1}[0-9]{1}[.]{1}\\d{0,2}|"
                                + //definition VOR 108,00 - 117,95
                                "[1]{1}[0-1]{1}[0-9]{1}[,]{1}\\d{0,2}");

                    }

                    // Parse for Navaid type VOR
                    if (QCode.startsWith("QNV")) {
                        //determine type of Navaid
                        output.setItemType("VOR");

                        //define regexpattern for this kind of navaid
                        p = Pattern.compile(
                                //definition VOR 108.00 - 117.95
                                "[1]{1}[0-1]{1}[0-9]{1}[.]{1}\\d{0,2}|"
                                + //definition VOR 108,00 - 117,95
                                "[1]{1}[0-1]{1}[0-9]{1}[,]{1}\\d{0,2}");

                    }

                    // Parse for Navaid type NDB  
                    if (QCode.startsWith("QNB")) {
                        //determine type of Navaid
                        output.setItemType("NDB");

                        //define regexpattern for this kind of navaid
                        p = Pattern.compile(
                                //definition NBD 300-3000
                                "[0-3]{1}[0-9]{3}");

                    }

                    Matcher m = p.matcher(cleanLineE.substring(cleanLineE.lastIndexOf("E) ")));

                    while (m.equals(NULL)) {
                        output.setItemFreq("-");
                        // break;
                    }

                    while (m.find()) {

                        System.out.println("Navaid Frequency identified >> " + m.group() + " " + m.start() + " " + m.end());
                        output.setItemFreq(m.group().replace(",", "."));
                        break;
                    }

// Step3. Check if the parsed navaid exists in XPL navaid database
                    NavaidFile neu = new NavaidFile();
                    int i;
                    for (i = 0; i < cleanReportSplitted.length; i++) {
                        if (cleanReportSplitted[i].length() == 2 | cleanReportSplitted[i].length() == 3) //System.out.println("Now cheking, if this Navaid is in the Navaid Database: " +cleanReportSplitted[i]);
                        {
                            if (neu.navaidExists(cleanReportSplitted[i])) {

                                System.out.println(cleanReportSplitted[i] + " found in Database Navaids");
                                //write the found and matching navaid to the output List
                                output.setItemId(cleanReportSplitted[i]);

                                break;
                            } else {
                                //System.out.println(cleanReportSplitted[i] + " not found in Database Navaids");
                            }
                        }

                    }

                }

                // write all other items into the actionList 
                try {
                    //output.setItemId(nt2.getCns_location_id()); this needs to be changed
                    output.setItemRegion(nt2.getIcao_id());
                    output.setItemLat("LAT");
                    output.setItemLon("LON");
                    output.setItemFrom("FROM");
                    output.setItemUntil("UNTIL");

                } catch (Exception e) {
                    System.out.println(e);
                }

                // Lets give the current ActionItem to the ActionFile
                this.ActionFileForXPlane.addToActionFile(this.output);
            }

        }

        System.out.println("End of processing");

        return processingNotam;
    }

}
