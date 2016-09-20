
package com.nkdroidsolutions.firedefence.model.Form3Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDatum {

    @SerializedName("Manufacturer")
    @Expose
    private String manufacturer = "";
    @SerializedName("type")
    @Expose
    private String type = "";
    @SerializedName("capacity")
    @Expose
    private String capacity = "";
    @SerializedName("location")
    @Expose
    private String location = "";
    @SerializedName("Weight")
    @Expose
    private String weight = "";
    @SerializedName("manu_date")
    @Expose
    private String manuDate = "";
    @SerializedName("update_label")
    @Expose
    private String updateLabel = "";
    @SerializedName("new_tag")
    @Expose
    private String newTag = "";
    @SerializedName("services")
    @Expose
    private String services = "";
    @SerializedName("comments")
    @Expose
    private String comments = "";

    /**
     * @return The manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer The Manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The capacity
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * @param capacity The capacity
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * @return The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight The Weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return The manuDate
     */
    public String getManuDate() {
        return manuDate;
    }

    /**
     * @param manuDate The manu_date
     */
    public void setManuDate(String manuDate) {
        this.manuDate = manuDate;
    }

    /**
     * @return The updateLabel
     */
    public String getUpdateLabel() {
        return updateLabel;
    }

    /**
     * @param updateLabel The update_label
     */
    public void setUpdateLabel(String updateLabel) {
        this.updateLabel = updateLabel;
    }

    /**
     * @return The newTag
     */
    public String getNewTag() {
        return newTag;
    }

    /**
     * @param newTag The new_tag
     */
    public void setNewTag(String newTag) {
        this.newTag = newTag;
    }

    /**
     * @return The services
     */
    public String getServices() {
        return services;
    }

    /**
     * @param services The services
     */
    public void setServices(String services) {
        this.services = services;
    }

    /**
     * @return The comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments The comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

}
