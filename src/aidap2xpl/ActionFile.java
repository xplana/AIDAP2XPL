/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidap2xpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author wdr
 */
public class ActionFile {

    ArrayList<ActionItem> ActionItemsList;

    private FileWriter writer;
    File f = new File("/Users/wdr/Desktop/output.txt");

    // Initialize OutputFile, make sure to delete this on every startup
    public void initializeActionFile() throws IOException {

        System.out.println("Initializing ActionFile");
        writer = new FileWriter(f);
        if (f.exists()) {
            System.out.println("ActionFile already exists: " + f.toString());
            writer.write("NOCH EIN TESeeeeeT");
        } else {

            writer = new FileWriter(f);
            System.out.println("A new ActionFile was created at : " + f.toString());
        }
    }

    // add line by line to the actionfile
    public ActionItem addToActionFile(ActionItem test) {
        
        ActionItem printit = new ActionItem();
        
        try (FileWriter fw = new FileWriter(f, true)    //the true will append the new data
        ) {

            fw.write(test.getItemId()+","+
                    test.getItemFreq()+","+
                    test.getItemLat()+","+
                    test.getItemLon()+","+
                    test.getItemRegion()+","+
                    test.getItemFrom()+","+
                    test.getItemUntil()+","+
                    test.getItemType()+","+
                    
                    "\n");//appends the string to the file
            
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
        return printit;
    }
}
//
