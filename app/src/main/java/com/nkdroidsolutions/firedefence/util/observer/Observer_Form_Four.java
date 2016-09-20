package com.nkdroidsolutions.firedefence.util.observer;

import com.nkdroidsolutions.firedefence.model.Form4Model.FormFourProp;

import java.util.Observable;

/**
 * Created by Sahil on 12-07-2016.
 */
public class Observer_Form_Four extends Observable {

    FormFourProp formfour = new FormFourProp();

    public FormFourProp getFormfour() {
        return formfour;
    }

    public void setFormfour(FormFourProp formfour) {
        this.formfour = formfour;
        setChanged();
        notifyObservers();
    }
}
