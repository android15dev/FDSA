package com.nkdroidsolutions.firedefence.util.observer;

import com.nkdroidsolutions.firedefence.model.allform.AllForm;

import java.util.Observable;

/**
 * Created by Sahil on 12-07-2016.
 */
public class Observer_AllForm extends Observable {

    AllForm allForm = new AllForm();

    public AllForm getAllForm() {
        return allForm;
    }

    public void setAllForm(AllForm allForm) {
        this.allForm = allForm;
        setChanged();
        notifyObservers();

    }
}
