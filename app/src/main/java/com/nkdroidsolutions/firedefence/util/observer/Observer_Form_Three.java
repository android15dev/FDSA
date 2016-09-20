package com.nkdroidsolutions.firedefence.util.observer;

import com.nkdroidsolutions.firedefence.model.Form3Model.FormThreeProp;

import java.util.Observable;

/**
 * Created by Sahil on 12-07-2016.
 */
public class Observer_Form_Three extends Observable {

    FormThreeProp formthree = new FormThreeProp();

    public FormThreeProp getFormthree() {
        return formthree;
    }

    public void setFormthree(FormThreeProp formthree) {
        this.formthree = formthree;
        setChanged();
        notifyObservers();
    }
}
