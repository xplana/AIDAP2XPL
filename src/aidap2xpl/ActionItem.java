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
public class ActionItem {
    
    private String 
            ItemId,         //Navaid identifier.. 
            ItemType,       //VOR, ILS ...
            ItemLat,
            ItemLon,
            ItemFrom,
            ItemFreq,
            ItemUntil,
            ItemMessage;    // RAW NOTAM message 

    /**
     * @return the ItemId
     */
    public String getItemId() {
        return ItemId;
    }

    /**
     * @param ItemId the ItemId to set
     */
    public void setItemId(String ItemId) {
        this.ItemId = ItemId;
    }

    /**
     * @return the ItemType
     */
    public String getItemType() {
        return ItemType;
    }

    /**
     * @param ItemType the ItemType to set
     */
    public void setItemType(String ItemType) {
        this.ItemType = ItemType;
    }

    /**
     * @return the ItemLat
     */
    public String getItemLat() {
        return ItemLat;
    }

    /**
     * @param ItemLat the ItemLat to set
     */
    public void setItemLat(String ItemLat) {
        this.ItemLat = ItemLat;
    }

    /**
     * @return the ItemLon
     */
    public String getItemLon() {
        return ItemLon;
    }

    /**
     * @param ItemLon the ItemLon to set
     */
    public void setItemLon(String ItemLon) {
        this.ItemLon = ItemLon;
    }

    /**
     * @return the ItemFrom
     */
    public String getItemFrom() {
        return ItemFrom;
    }

    /**
     * @param ItemFrom the ItemFrom to set
     */
    public void setItemFrom(String ItemFrom) {
        this.ItemFrom = ItemFrom;
    }

    /**
     * @return the ItemUntil
     */
    public String getItemUntil() {
        return ItemUntil;
    }

    /**
     * @param ItemUntil the ItemUntil to set
     */
    public void setItemUntil(String ItemUntil) {
        this.ItemUntil = ItemUntil;
    }

    /**
     * @return the ItemMessage
     */
    public String getItemMessage() {
        return ItemMessage;
    }

    /**
     * @param ItemMessage the ItemMessage to set
     */
    public void setItemMessage(String ItemMessage) {
        this.ItemMessage = ItemMessage;
    }

    /**
     * @return the ItemFreq
     */
    public String getItemFreq() {
        return ItemFreq;
    }

    /**
     * @param ItemFreq the ItemFreq to set
     */
    public void setItemFreq(String ItemFreq) {
        this.ItemFreq = ItemFreq;
    }
    
    
            
    
}
