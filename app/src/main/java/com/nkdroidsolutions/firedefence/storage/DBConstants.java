package com.nkdroidsolutions.firedefence.storage;

/**
 * Created by Sahil on 03-09-2016.
 */
public interface DBConstants {

    enum LOCALFORM {
        TB_LOCAL_FORM, ID, TYPE, FORM_ID, DATA;
    }

    enum SERVERFORM {
        TB_SERVER_FORM, ID, TYPE, FORM_USER_ID, ISNEW, ACTION, DATA;
    }

}
