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
public class debug {
    
    Settings setting = new Settings();
    
    
    public void printToScreen (String message) {
        
        if (setting.getDebugOn()==true) {
            System.out.println("");
        }
    }
    
    
}
