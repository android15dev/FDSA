package com.nkdroidsolutions.firedefence.util.observer;

import com.nkdroidsolutions.firedefence.model.Form1Model.FormOneProp;

import java.util.Observable;

/**
 * Created by Sahil on 12-07-2016.
 */
public class Observer_Form_One extends Observable {

    FormOneProp formone = new FormOneProp();

    public FormOneProp getFormone() {
        return formone;
    }

    public void setFormone(FormOneProp formone) {
        this.formone = formone;
        setChanged();
        notifyObservers();
    }
}
