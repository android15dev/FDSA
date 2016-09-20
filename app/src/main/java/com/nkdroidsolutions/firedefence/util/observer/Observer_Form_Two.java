package com.nkdroidsolutions.firedefence.util.observer;

import com.nkdroidsolutions.firedefence.model.Form2Model.FormTwoProp;

import java.util.Observable;

/**
 * Created by Sahil on 12-07-2016.
 */
public class Observer_Form_Two extends Observable {

    FormTwoProp formTwo = new FormTwoProp();

    public FormTwoProp getFormTwo() {
        return formTwo;
    }

    public void setFormTwo(FormTwoProp formTwo) {
        this.formTwo = formTwo;
        setChanged();
        notifyObservers();
    }
}
