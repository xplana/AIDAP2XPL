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
public class Settings {
    private String XPL_Path = "/Applications/X-Plane 10/Custom Data/GNS430/navdata/Navaids.TXT";
    private String NotamsXMLFile = "/Users/wdr/Desktop/notam_I.XML";
    private String ActionFileXPlane = "/Users/wdr/Desktop/output.TXT";
    private Boolean debugOn = false;
    private Integer debugLevel = 1;         //1=only important notes, 0=all

    /**
     * @return the XPL_Path
     */
    public String getXPL_Path() {
        return XPL_Path;
    }

    /**
     * @param XPL_Path the XPL_Path to set
     */
    public void setXPL_Path(String XPL_Path) {
        this.XPL_Path = XPL_Path;
    }

    /**
     * @return the NotamsXMLFile
     */
    public String getNotamsXMLFile() {
        return NotamsXMLFile;
    }

    /**
     * @param NotamsXMLFile the NotamsXMLFile to set
     */
    public void setNotamsXMLFile(String NotamsXMLFile) {
        this.NotamsXMLFile = NotamsXMLFile;
    }

    /**
     * @return the debugOn
     */
    public Boolean getDebugOn() {
        return debugOn;
    }

    /**
     * @param debugOn the debugOn to set
     */
    public void setDebugOn(Boolean debugOn) {
        this.debugOn = debugOn;
    }

    /**
     * @return the ActionFileXPlane
     */
    public String getActionFileXPlane() {
        return ActionFileXPlane;
    }

    /**
     * @param ActionFileXPlane the ActionFileXPlane to set
     */
    public void setActionFileXPlane(String ActionFileXPlane) {
        this.ActionFileXPlane = ActionFileXPlane;
    }

    /**
     * @return the debugLevel
     */
    public Integer getDebugLevel() {
        return debugLevel;
    }

    /**
     * @param debugLevel the debugLevel to set
     */
    public void setDebugLevel(Integer debugLevel) {
        this.debugLevel = debugLevel;
    }
    
}
