
package com.nkdroidsolutions.firedefence.model.Form4Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Engine {

    @SerializedName("engine_oil_chk")
    @Expose
    private String engineOilChk = "0";
    @SerializedName("engine_oil_comment")
    @Expose
    private String engineOilComment = "";
    @SerializedName("coolant_lvl_chk")
    @Expose
    private String coolantLvlChk = "0";
    @SerializedName("coolant_lvl_comment")
    @Expose
    private String coolantLvlComment = "";
    @SerializedName("brake_fluid_lvl_chk")
    @Expose
    private String brakeFluidLvlChk = "0";
    @SerializedName("brake_fluid_lvl_comment")
    @Expose
    private String brakeFluidLvlComment = "";
    @SerializedName("steering_fluid_lvl_chk")
    @Expose
    private String steeringFluidLvlChk = "0";
    @SerializedName("steering_fluid_lvl_comment")
    @Expose
    private String steeringFluidLvlComment = "";
    @SerializedName("Washer_fluid_lvl_chk")
    @Expose
    private String washerFluidLvlChk = "0";
    @SerializedName("Washer_fluid_lvl_comment")
    @Expose
    private String washerFluidLvlComment = "";
    @SerializedName("washer_wiper_chk")
    @Expose
    private String washerWiperChk = "0";
    @SerializedName("washer_wiper_comment")
    @Expose
    private String washerWiperComment = "";
    @SerializedName("light_horn_chk")
    @Expose
    private String lightHornChk = "0";
    @SerializedName("light_horn_comment")
    @Expose
    private String lightHornComment = "";
    @SerializedName("tyre_tread_sidewalls_chk")
    @Expose
    private String tyreTreadSidewallsChk = "0";
    @SerializedName("tyre_tread_sidewalls_comment")
    @Expose
    private String tyreTreadSidewallsComment = "";
    @SerializedName("tyre_pressures_chk")
    @Expose
    private String tyrePressuresChk = "0";
    @SerializedName("tyre_pressures_comment")
    @Expose
    private String tyrePressuresComment = "";
    @SerializedName("wheel_nuts_secure_chk")
    @Expose
    private String wheelNutsSecureChk = "0";
    @SerializedName("wheel_nuts_secure_comment")
    @Expose
    private String wheelNutsSecureComment = "";
    @SerializedName("condition_battery_chk")
    @Expose
    private String conditionBatteryChk = "0";
    @SerializedName("condition_battery_comment")
    @Expose
    private String conditionBatteryComment = "";
    @SerializedName("bodywk_chk")
    @Expose
    private String bodywkChk = "0";
    @SerializedName("bodywk_chk_comment")
    @Expose
    private String bodywkChkComment = "";
    @SerializedName("bodywk_chk_image")
    @Expose
    private String bodywkChkImage = "";

    private Bitmap bodywkChkBitmap;

    @SerializedName("first_aid_kid_content_chk")
    @Expose
    private String firstAidKidContentChk = "0";
    @SerializedName("first_aid_kid_content_comment")
    @Expose
    private String firstAidKidContentComment = "";
    @SerializedName("fire_exteingusher_chk")
    @Expose
    private String fireExteingusherChk = "0";
    @SerializedName("fire_exteingusher_comment")
    @Expose
    private String fireExteingusherComment = "";
    @SerializedName("clean_tidy_chk")
    @Expose
    private String cleanTidyChk = "0";
    @SerializedName("clean_tidy_comment")
    @Expose
    private String cleanTidyComment = "";
    @SerializedName("break_pads_disks_chk")
    @Expose
    private String breakPadsDisksChk = "0";
    @SerializedName("break_pads_disks_comment")
    @Expose
    private String breakPadsDisksComment = "";

    public Bitmap getBodywkChkBitmap() {
        return bodywkChkBitmap;
    }

    public void setBodywkChkBitmap(Bitmap bodywkChkBitmap) {
        this.bodywkChkBitmap = bodywkChkBitmap;
    }

    /**
     * @return The engineOilChk
     */
    public String getEngineOilChk() {
        return engineOilChk;
    }

    /**
     * @param engineOilChk The engine_oil_chk
     */
    public void setEngineOilChk(String engineOilChk) {
        this.engineOilChk = engineOilChk;
    }

    /**
     * @return The engineOilComment
     */
    public String getEngineOilComment() {
        return engineOilComment;
    }

    /**
     * @param engineOilComment The engine_oil_comment
     */
    public void setEngineOilComment(String engineOilComment) {
        this.engineOilComment = engineOilComment;
    }

    /**
     * @return The coolantLvlChk
     */
    public String getCoolantLvlChk() {
        return coolantLvlChk;
    }

    /**
     * @param coolantLvlChk The coolant_lvl_chk
     */
    public void setCoolantLvlChk(String coolantLvlChk) {
        this.coolantLvlChk = coolantLvlChk;
    }

    /**
     * @return The coolantLvlComment
     */
    public String getCoolantLvlComment() {
        return coolantLvlComment;
    }

    /**
     * @param coolantLvlComment The coolant_lvl_comment
     */
    public void setCoolantLvlComment(String coolantLvlComment) {
        this.coolantLvlComment = coolantLvlComment;
    }

    /**
     * @return The brakeFluidLvlChk
     */
    public String getBrakeFluidLvlChk() {
        return brakeFluidLvlChk;
    }

    /**
     * @param brakeFluidLvlChk The brake_fluid_lvl_chk
     */
    public void setBrakeFluidLvlChk(String brakeFluidLvlChk) {
        this.brakeFluidLvlChk = brakeFluidLvlChk;
    }

    /**
     * @return The brakeFluidLvlComment
     */
    public String getBrakeFluidLvlComment() {
        return brakeFluidLvlComment;
    }

    /**
     * @param brakeFluidLvlComment The brake_fluid_lvl_comment
     */
    public void setBrakeFluidLvlComment(String brakeFluidLvlComment) {
        this.brakeFluidLvlComment = brakeFluidLvlComment;
    }

    /**
     * @return The steeringFluidLvlChk
     */
    public String getSteeringFluidLvlChk() {
        return steeringFluidLvlChk;
    }

    /**
     * @param steeringFluidLvlChk The steering_fluid_lvl_chk
     */
    public void setSteeringFluidLvlChk(String steeringFluidLvlChk) {
        this.steeringFluidLvlChk = steeringFluidLvlChk;
    }

    /**
     * @return The steeringFluidLvlComment
     */
    public String getSteeringFluidLvlComment() {
        return steeringFluidLvlComment;
    }

    /**
     * @param steeringFluidLvlComment The steering_fluid_lvl_comment
     */
    public void setSteeringFluidLvlComment(String steeringFluidLvlComment) {
        this.steeringFluidLvlComment = steeringFluidLvlComment;
    }

    /**
     * @return The washerFluidLvlChk
     */
    public String getWasherFluidLvlChk() {
        return washerFluidLvlChk;
    }

    /**
     * @param washerFluidLvlChk The Washer_fluid_lvl_chk
     */
    public void setWasherFluidLvlChk(String washerFluidLvlChk) {
        this.washerFluidLvlChk = washerFluidLvlChk;
    }

    /**
     * @return The washerFluidLvlComment
     */
    public String getWasherFluidLvlComment() {
        return washerFluidLvlComment;
    }

    /**
     * @param washerFluidLvlComment The Washer_fluid_lvl_comment
     */
    public void setWasherFluidLvlComment(String washerFluidLvlComment) {
        this.washerFluidLvlComment = washerFluidLvlComment;
    }

    /**
     * @return The washerWiperChk
     */
    public String getWasherWiperChk() {
        return washerWiperChk;
    }

    /**
     * @param washerWiperChk The washer_wiper_chk
     */
    public void setWasherWiperChk(String washerWiperChk) {
        this.washerWiperChk = washerWiperChk;
    }

    /**
     * @return The washerWiperComment
     */
    public String getWasherWiperComment() {
        return washerWiperComment;
    }

    /**
     * @param washerWiperComment The washer_wiper_comment
     */
    public void setWasherWiperComment(String washerWiperComment) {
        this.washerWiperComment = washerWiperComment;
    }

    /**
     * @return The lightHornChk
     */
    public String getLightHornChk() {
        return lightHornChk;
    }

    /**
     * @param lightHornChk The light_horn_chk
     */
    public void setLightHornChk(String lightHornChk) {
        this.lightHornChk = lightHornChk;
    }

    /**
     * @return The lightHornComment
     */
    public String getLightHornComment() {
        return lightHornComment;
    }

    /**
     * @param lightHornComment The light_horn_comment
     */
    public void setLightHornComment(String lightHornComment) {
        this.lightHornComment = lightHornComment;
    }

    /**
     * @return The tyreTreadSidewallsChk
     */
    public String getTyreTreadSidewallsChk() {
        return tyreTreadSidewallsChk;
    }

    /**
     * @param tyreTreadSidewallsChk The tyre_tread_sidewalls_chk
     */
    public void setTyreTreadSidewallsChk(String tyreTreadSidewallsChk) {
        this.tyreTreadSidewallsChk = tyreTreadSidewallsChk;
    }

    /**
     * @return The tyreTreadSidewallsComment
     */
    public String getTyreTreadSidewallsComment() {
        return tyreTreadSidewallsComment;
    }

    /**
     * @param tyreTreadSidewallsComment The tyre_tread_sidewalls_comment
     */
    public void setTyreTreadSidewallsComment(String tyreTreadSidewallsComment) {
        this.tyreTreadSidewallsComment = tyreTreadSidewallsComment;
    }

    /**
     * @return The tyrePressuresChk
     */
    public String getTyrePressuresChk() {
        return tyrePressuresChk;
    }

    /**
     * @param tyrePressuresChk The tyre_pressures_chk
     */
    public void setTyrePressuresChk(String tyrePressuresChk) {
        this.tyrePressuresChk = tyrePressuresChk;
    }

    /**
     * @return The tyrePressuresComment
     */
    public String getTyrePressuresComment() {
        return tyrePressuresComment;
    }

    /**
     * @param tyrePressuresComment The tyre_pressures_comment
     */
    public void setTyrePressuresComment(String tyrePressuresComment) {
        this.tyrePressuresComment = tyrePressuresComment;
    }

    /**
     * @return The wheelNutsSecureChk
     */
    public String getWheelNutsSecureChk() {
        return wheelNutsSecureChk;
    }

    /**
     * @param wheelNutsSecureChk The wheel_nuts_secure_chk
     */
    public void setWheelNutsSecureChk(String wheelNutsSecureChk) {
        this.wheelNutsSecureChk = wheelNutsSecureChk;
    }

    /**
     * @return The wheelNutsSecureComment
     */
    public String getWheelNutsSecureComment() {
        return wheelNutsSecureComment;
    }

    /**
     * @param wheelNutsSecureComment The wheel_nuts_secure_comment
     */
    public void setWheelNutsSecureComment(String wheelNutsSecureComment) {
        this.wheelNutsSecureComment = wheelNutsSecureComment;
    }

    /**
     * @return The conditionBatteryChk
     */
    public String getConditionBatteryChk() {
        return conditionBatteryChk;
    }

    /**
     * @param conditionBatteryChk The condition_battery_chk
     */
    public void setConditionBatteryChk(String conditionBatteryChk) {
        this.conditionBatteryChk = conditionBatteryChk;
    }

    /**
     * @return The conditionBatteryComment
     */
    public String getConditionBatteryComment() {
        return conditionBatteryComment;
    }

    /**
     * @param conditionBatteryComment The condition_battery_comment
     */
    public void setConditionBatteryComment(String conditionBatteryComment) {
        this.conditionBatteryComment = conditionBatteryComment;
    }

    /**
     * @return The bodywkChk
     */
    public String getBodywkChk() {
        return bodywkChk;
    }

    /**
     * @param bodywkChk The bodywk_chk
     */
    public void setBodywkChk(String bodywkChk) {
        this.bodywkChk = bodywkChk;
    }

    /**
     * @return The bodywkChkComment
     */
    public String getBodywkChkComment() {
        return bodywkChkComment;
    }

    /**
     * @param bodywkChkComment The bodywk_chk_comment
     */
    public void setBodywkChkComment(String bodywkChkComment) {
        this.bodywkChkComment = bodywkChkComment;
    }

    /**
     * @return The bodywkChkImage
     */
    public String getBodywkChkImage() {
        return bodywkChkImage;
    }

    /**
     * @param bodywkChkImage The bodywk_chk_image
     */
    public void setBodywkChkImage(String bodywkChkImage) {
        this.bodywkChkImage = bodywkChkImage;
    }

    /**
     * @return The firstAidKidContentChk
     */
    public String getFirstAidKidContentChk() {
        return firstAidKidContentChk;
    }

    /**
     * @param firstAidKidContentChk The first_aid_kid_content_chk
     */
    public void setFirstAidKidContentChk(String firstAidKidContentChk) {
        this.firstAidKidContentChk = firstAidKidContentChk;
    }

    /**
     * @return The firstAidKidContentComment
     */
    public String getFirstAidKidContentComment() {
        return firstAidKidContentComment;
    }

    /**
     * @param firstAidKidContentComment The first_aid_kid_content_comment
     */
    public void setFirstAidKidContentComment(String firstAidKidContentComment) {
        this.firstAidKidContentComment = firstAidKidContentComment;
    }

    /**
     * @return The fireExteingusherChk
     */
    public String getFireExteingusherChk() {
        return fireExteingusherChk;
    }

    /**
     * @param fireExteingusherChk The fire_exteingusher_chk
     */
    public void setFireExteingusherChk(String fireExteingusherChk) {
        this.fireExteingusherChk = fireExteingusherChk;
    }

    /**
     * @return The fireExteingusherComment
     */
    public String getFireExteingusherComment() {
        return fireExteingusherComment;
    }

    /**
     * @param fireExteingusherComment The fire_exteingusher_comment
     */
    public void setFireExteingusherComment(String fireExteingusherComment) {
        this.fireExteingusherComment = fireExteingusherComment;
    }

    /**
     * @return The cleanTidyChk
     */
    public String getCleanTidyChk() {
        return cleanTidyChk;
    }

    /**
     * @param cleanTidyChk The clean_tidy_chk
     */
    public void setCleanTidyChk(String cleanTidyChk) {
        this.cleanTidyChk = cleanTidyChk;
    }

    /**
     * @return The cleanTidyComment
     */
    public String getCleanTidyComment() {
        return cleanTidyComment;
    }

    /**
     * @param cleanTidyComment The clean_tidy_comment
     */
    public void setCleanTidyComment(String cleanTidyComment) {
        this.cleanTidyComment = cleanTidyComment;
    }

    /**
     * @return The breakPadsDisksChk
     */
    public String getBreakPadsDisksChk() {
        return breakPadsDisksChk;
    }

    /**
     * @param breakPadsDisksChk The break_pads_disks_chk
     */
    public void setBreakPadsDisksChk(String breakPadsDisksChk) {
        this.breakPadsDisksChk = breakPadsDisksChk;
    }

    /**
     * @return The breakPadsDisksComment
     */
    public String getBreakPadsDisksComment() {
        return breakPadsDisksComment;
    }

    /**
     * @param breakPadsDisksComment The break_pads_disks_comment
     */
    public void setBreakPadsDisksComment(String breakPadsDisksComment) {
        this.breakPadsDisksComment = breakPadsDisksComment;
    }

}
