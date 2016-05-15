/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidap2xpl;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author wdr
 */
public class AIDAP2XPL {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        
        // Import Navaids from X-Plane Navaids.txt file
        NavaidFile navaidContainer = new NavaidFile();

        // Einlesen der Notmas-File    
        NotamsFile notams = new NotamsFile();
        notams.identifyPossibleNavaids("QNMAS");

        //Parse Notams-File
    
        //navaidContainer.navaidExists("LBU");
        //Navaid getExisting = navaidContainer.getExistingNavaid("TAU");
        //      getExisting.getVorName();
    }

}
