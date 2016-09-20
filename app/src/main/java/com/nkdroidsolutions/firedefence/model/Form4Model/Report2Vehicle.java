
package com.nkdroidsolutions.firedefence.model.Form4Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Report2Vehicle {

    @SerializedName("engine")
    @Expose
    private List<Engine> engine = new ArrayList<Engine>(16);

    /**
     * @return The engine
     */
    public List<Engine> getEngine() {
        return engine;
    }

    /**
     * @param engine The engine
     */
    public void setEngine(List<Engine> engine) {
        this.engine = engine;
    }

}
