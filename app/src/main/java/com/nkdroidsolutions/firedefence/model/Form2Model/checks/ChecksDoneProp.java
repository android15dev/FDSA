
package com.nkdroidsolutions.firedefence.model.Form2Model.checks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChecksDoneProp {

    @SerializedName("quarterly")
    @Expose
    private List<Quarterly> quarterly = new ArrayList<Quarterly>(21);
    @SerializedName("half_yearly")
    @Expose
    private List<HalfYearly> halfYearly = new ArrayList<HalfYearly>(21);
    @SerializedName("yearly")
    @Expose
    private List<Yearly> yearly = new ArrayList<Yearly>(7);
    @SerializedName("trip_test")
    @Expose
    private List<TripTest> tripTest = new ArrayList<TripTest>(6);

    /**
     * @return The quarterly
     */
    public List<Quarterly> getQuarterly() {
        return quarterly;
    }

    /**
     * @param quarterly The quarterly
     */
    public void setQuarterly(List<Quarterly> quarterly) {
        this.quarterly = quarterly;
    }

    /**
     * @return The halfYearly
     */
    public List<HalfYearly> getHalfYearly() {
        return halfYearly;
    }

    /**
     * @param halfYearly The half_yearly
     */
    public void setHalfYearly(List<HalfYearly> halfYearly) {
        this.halfYearly = halfYearly;
    }

    /**
     * @return The yearly
     */
    public List<Yearly> getYearly() {
        return yearly;
    }

    /**
     * @param yearly The yearly
     */
    public void setYearly(List<Yearly> yearly) {
        this.yearly = yearly;
    }

    /**
     * @return The tripTest
     */
    public List<TripTest> getTripTest() {
        return tripTest;
    }

    /**
     * @param tripTest The trip_test
     */
    public void setTripTest(List<TripTest> tripTest) {
        this.tripTest = tripTest;
    }

}
