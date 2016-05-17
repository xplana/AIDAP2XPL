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
        
        // TODO Do some checks like
        // * does the navaid file exist
        // * does the notams exist
        // * does the settings-file exist

        // Import Navaids from X-Plane Navaids.txt file
        NavaidFile navaidContainer = new NavaidFile();

        // TODO Import the Q-Codes and UIR/FIRS
        // 
        
        
        // Import Notams File    
        NotamsFile notams = new NotamsFile();
        
        
        
        //Initialize the File we want to write the actionItems for X-Plane in
        ActionFile output = new ActionFile();
        output.initializeActionFile();
        

        // Parse and write Notams-File for 
        notams.identifyPossibleNavaids("QNMAS");
        notams.identifyPossibleNavaids("QNMAW");
    
        //navaidContainer.navaidExists("LBU");
        //Navaid getExisting = navaidContainer.getExistingNavaid("TAU");
        //      getExisting.getVorName();
    }

}
