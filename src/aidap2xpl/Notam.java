/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aidap2xpl;

/**
 *
 * @author wdr
 */
public class Notam {
    
//    <notam-rec>
//<source_id>I</source_id>
//<account_id>AGGHYNYX</account_id>
//<notam_id>A0043/16</notam_id>
//<notam_part>1</notam_part>
//<cns_location_id>AGGH</cns_location_id>
//<icao_id>AGGH</icao_id>
//<icao_name>HONIARA INTL</icao_name>
//<total_parts>1</total_parts>
//<notam_effective_dtg>201604050100</notam_effective_dtg>
//<notam_lastmod_dtg>201604140359</notam_lastmod_dtg>
//<notam_text>A0043/16 NOTAMR A0040/16
//Q) AGGG/QFWXX/V/M/A/000/999
//A) AGGH
//B) 1604050100
//C) 1605310100 EST
//E) RWY 24 WDI OBST LGT U/S</notam_text>
//<notam_report>RWY 24 WDI OBST LGT U/S. 05 APR 01:00 2016 UNTIL 31 MAY 01:00 2016 ESTIMATED. CREATED: 14 APR 03:56 2016</notam_report>
//<notam_nrc>NOTAMR</notam_nrc>
//<notam_qcode>QFW
    
    private String 
            notam_id, 
            cns_location_id, 
            icao_id,
            icao_name, 
            notam_effective_dtg, 
            notam_lastmod_dtg, 
            notam_text,
            notam_report;
            
         
    
    
    

    /**
     * @return the notam_id
     */
    public String getNotam_id() {
        return notam_id;
    }

    /**
     * @param notam_id the notam_id to set
     */
    public void setNotam_id(String notam_id) {
        this.notam_id = notam_id;
    }

    /**
     * @return the cns_location_id
     */
    public String getCns_location_id() {
        return cns_location_id;
    }

    /**
     * @param cns_location_id the cns_location_id to set
     */
    public void setCns_location_id(String cns_location_id) {
        this.cns_location_id = cns_location_id;
    }

    /**
     * @return the icao_name
     */
    public String getIcao_name() {
        return icao_name;
    }

    /**
     * @param icao_name the icao_name to set
     */
    public void setIcao_name(String icao_name) {
        this.icao_name = icao_name;
    }

    /**
     * @return the notam_effective_dtg
     */
    public String getNotam_effective_dtg() {
        return notam_effective_dtg;
    }

    /**
     * @param notam_effective_dtg the notam_effective_dtg to set
     */
    public void setNotam_effective_dtg(String notam_effective_dtg) {
        this.notam_effective_dtg = notam_effective_dtg;
    }

    /**
     * @return the notam_lastmod_dtg
     */
    public String getNotam_lastmod_dtg() {
        return notam_lastmod_dtg;
    }

    /**
     * @param notam_lastmod_dtg the notam_lastmod_dtg to set
     */
    public void setNotam_lastmod_dtg(String notam_lastmod_dtg) {
        this.notam_lastmod_dtg = notam_lastmod_dtg;
    }

    /**
     * @return the notam_text
     */
    public String getNotam_text() {
        return notam_text;
    }

    /**
     * @param notam_text the notam_text to set
     */
    public void setNotam_text(String notam_text) {
        this.notam_text = notam_text;
    }

    

    /**
     * @return the icao_id
     */
    public String getIcao_id() {
        return icao_id;
    }

    /**
     * @param icao_id the icao_id to set
     */
    public void setIcao_id(String icao_id) {
        this.icao_id = icao_id;
    }

    /**
     * @return the notam_report
     */
    public String getNotam_report() {
        return notam_report;
    }

    /**
     * @param notam_report the notam_report to set
     */
    public void setNotam_report(String notam_report) {
        this.notam_report = notam_report;
    }
    
    
    
    
}
