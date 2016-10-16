package com.nkdroidsolutions.firedefence.model;

/**
 * Created by nirav on 09/08/15.
 */
public class AppConstant {

    public static final String BASE_URL = "http://fire-defence.net/ws/";

    public static final String ACTION_GETALLFORM_WITHDATE = "return_user_forms1";

    public static final String ACTION_GETALLFORM = "return_user_forms";
    public static final String ACTION_GETFORMDETAIL = "return_form_data";

    public static final String ACTION_UPDATE_FORM_ONE = "update_formone_data";
    public static final String ACTION_UPDATE_FORM_TWO = "update_formtwo_data";
    public static final String ACTION_UPDATE_FORM_THREE = "update_formthree_data";
    public static final String ACTION_UPDATE_FORM_FOUR = "update_formfour_data";

    public static final String ACTION_SAVE_FORM_ONE = "save_formone_data";
    public static final String ACTION_SAVE_FORM_TWO = "save_formtwo_data";
    public static final String ACTION_SAVE_FORM_THREE = "save_formthree_data";
    public static final String ACTION_SAVE_FORM_FOUR = "save_formfour_data";

    public final static String FILE_NAME_TEMPLATE = "avatar_photo_tmp";

    public static final String IMAGE_URL = "http://fire-defence.net/app/upload/";

    public static final String DATA = "data";

    public static final String LOGIN = "http://fire-defence.net/app/webservices/logincheck";
    public static final String FORGOT_PASSWORD = "http://www.fire-defence.net/app/webservices/forgetpassword";
    public static final String SUBMIT_FORM = "http://www.fire-defence.net/app/webservices/checkingimage";
    public static final String GET_FORMS_LIST = "http://www.fire-defence.net/app/webservices/formonedata";
    //Connection Checker
    public static final int TYPE_NOT_CONNECTED = 0;
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_2G = 3;
    public static final int TYPE_3G = 4;
    public static final int TYPE_4G = 5;

    public static final String STEP_1 = "Fill Form";
    public static final String STEP_2 = "Ready To Sync";
    public static final String STEP_3 = "Sync Done";

    public static final String FOLDER_NAME = "FDS";


    public static final int REQUEST_CODE_TAKE_PICTURE = 6;
    public static final int REQUEST_CODE_ADD_DEFECT = 7;
    public static final int REQUEST_CODE_ADD_FORM3 = 8;
    public static final String IS_NEW = "is_new";
}
