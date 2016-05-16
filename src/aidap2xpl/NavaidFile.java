/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidap2xpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author wdr
 */
public final class NavaidFile {

    ArrayList<Navaid> meinListe;

    // Read a config file where the path to X-planes Navaid File is stored
    // Check if the Navaid File exists
    // read Navaid File (this normally takes X-planes original Navaids File) into
    // an array
    public NavaidFile() {

        this.meinListe = new ArrayList<>();
        dateiEinlesen("/Users/wdr/Desktop/Navaids.txt");
    }
    
    public void dateiEinlesen(String file) {

        final ArrayList<String[]> lines = new ArrayList<>();
        FileReader myFile;
        BufferedReader buff;

//        System.out.println("...\r\nProcessing Navaid import from: " + file);

        try {

            myFile = new FileReader(file);
            buff = new BufferedReader(myFile);
            String zeile;

            while ((zeile = buff.readLine()) != null) {

                lines.add(0, zeile.split(","));
                String[] array = zeile.split("\\,");

                Navaid ndc = new Navaid();
                ndc.setVorId(array[0]);
                ndc.setVorName(array[1]);
                ndc.setVorFreq(array[2]);
                ndc.setVorSettingOne(array[3]);
                ndc.setVorSettingTwo(array[4]);
                ndc.setVorSettingThree(array[5]);
                ndc.setVorLat(array[6]);
                ndc.setVorLon(array[7]);

                this.meinListe.add(ndc);

            }
            //System.out.println("Total Navaids imported: " + this.meinListe.size());

        } catch (IOException e) {
        }

    }

    public void identifyNavaids() {
        System.out.println("Trying to identify possible Navaid matches in NOTAMS");
    }

    public boolean navaidExists(String vorId, String freq) {

        for (Navaid ndcTemp : meinListe) {
            if (ndcTemp.getVorId().equals(vorId)) {
                
                if(ndcTemp.getVorFreq().contains(freq)) {
                    return true;
                }
                //System.out.println(ndcTemp.getVorId());
                
            }
        }
        //System.out.println(vorId + " nicht gefunden");
        return false;

    }

    public Navaid getExistingNavaid(String vorId) {
        Navaid ndcTemp = new Navaid();
        for (Navaid ndcTemp2 : meinListe) {
            if (ndcTemp.getVorId().equals(vorId)) {
                System.out.println(ndcTemp.getVorId() + " " + ndcTemp.getVorFreq());
                return ndcTemp2;
            }
        }

        return ndcTemp;
    }

}
