package com.nkdroidsolutions.firedefence.web_api;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.nkdroidsolutions.firedefence.util.FunctionUtils;

/**
 * Created by Sahil on 17-07-2016.
 */
public class Base64ToBitmap extends AsyncTask<String, String, Bitmap> {

    GetBitmap getBitmap;

    @Override
    protected Bitmap doInBackground(String... params) {

        Bitmap bit = null;
        try {
            bit = FunctionUtils.getInstance().encodeToBitmap(params[0]);
        } catch (Exception e) {
            getBitmap.onError(e);
        }

        return bit;
    }

    @Override
    protected void onPostExecute(Bitmap s) {
        super.onPostExecute(s);

        try {
            if (s == null) {
                getBitmap.onError(new NullPointerException());
            } else {
                getBitmap.onSuccess(s);
            }
        } catch (Exception e) {
            getBitmap.onError(e);
        }

    }

    public void enqueue(GetBitmap getBitmap) {
        this.getBitmap = getBitmap;
    }

}
