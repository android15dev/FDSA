package com.nkdroidsolutions.firedefence.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nkdroidsolutions.firedefence.model.LocalFormProp;
import com.nkdroidsolutions.firedefence.model.ServerFormProp;

/**
 * Created by Sahil on 5/9/2016.
 */

public class Database extends SQLiteOpenHelper implements DBConstants {

    public static final String DbName = "Firebase.db";
    public static final int version = 1;
    SQLiteDatabase db;

    public Database(Context context) {
        super(context, DbName, null, version);
        db = this.getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        db.execSQL(Session.queryCreateTable);
        Session.insertDefaultValues(db);

        String local_form_query = "CREATE TABLE " + LOCALFORM.TB_LOCAL_FORM + "(" + LOCALFORM.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LOCALFORM.TYPE + " TEXT, "
                + LOCALFORM.FORM_ID + " TEXT, "
                + LOCALFORM.DATA + " TEXT)";
        db.execSQL(local_form_query);

        String server_form_query = "CREATE TABLE " + SERVERFORM.TB_SERVER_FORM + "(" + SERVERFORM.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SERVERFORM.TYPE + " TEXT, "
                + SERVERFORM.FORM_USER_ID + " TEXT, "
                + SERVERFORM.ISNEW + " TEXT, "
                + SERVERFORM.ACTION + " TEXT, "
                + SERVERFORM.DATA + " TEXT)";
        db.execSQL(server_form_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        this.db = sqLiteDatabase;
    }

    public ServerFormProp getServerForm() {
        SQLiteDatabase db_read = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + SERVERFORM.TB_SERVER_FORM;
        Cursor c = db_read.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            ServerFormProp prop = new ServerFormProp();
            prop.setId(c.getInt((c.getColumnIndex(SERVERFORM.ID + ""))));
            prop.setForm_user_id(c.getString((c.getColumnIndex(SERVERFORM.FORM_USER_ID + ""))));
            prop.setForm_isnew(c.getString((c.getColumnIndex(SERVERFORM.ISNEW + ""))));
            prop.setForm_action(c.getString((c.getColumnIndex(SERVERFORM.ACTION + ""))));
            prop.setForm_type(c.getString((c.getColumnIndex(SERVERFORM.TYPE + ""))));
            prop.setForm_data(c.getString((c.getColumnIndex(SERVERFORM.DATA + ""))));
            return prop;
        }
        db_read.close();
        return null;
    }

    public int addServerForm(ServerFormProp serverFormProp) {

        if (!serverFormProp.getForm_isnew().equals("true")) {

            SQLiteDatabase db_read = this.getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + SERVERFORM.TB_SERVER_FORM + " WHERE "
                    + SERVERFORM.FORM_USER_ID + "='" + serverFormProp.getForm_user_id() + "' and "
                    + SERVERFORM.TYPE + "='" + serverFormProp.getForm_type() + "'";
            Cursor c = db_read.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {

                int id = c.getInt((c.getColumnIndex(SERVERFORM.ID + "")));

                String where = SERVERFORM.ID + "=?";
                String[] whereArgs = new String[]{String.valueOf(id)};
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues value = new ContentValues();
                value.put(SERVERFORM.DATA + "", serverFormProp.getForm_data());
                int res = (int) db.update(SERVERFORM.TB_SERVER_FORM + "", value, where, whereArgs);
                db.close();

                db_read.close();
                Log.d("Updated OLD", res + "");
                return res;
            } else {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues value = new ContentValues();
                value.put(SERVERFORM.TYPE + "", serverFormProp.getForm_type());
                value.put(SERVERFORM.FORM_USER_ID + "", serverFormProp.getForm_user_id());
                value.put(SERVERFORM.ISNEW + "", serverFormProp.getForm_isnew());
                value.put(SERVERFORM.ACTION + "", serverFormProp.getForm_action());
                value.put(SERVERFORM.DATA + "", serverFormProp.getForm_data());
                int res = (int) db.insert(SERVERFORM.TB_SERVER_FORM + "", null, value);
                db.close();
                db_read.close();
                Log.d("Inserted OLD", res + "");
                return res;
            }
        } else {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(SERVERFORM.TYPE + "", serverFormProp.getForm_type());
            value.put(SERVERFORM.FORM_USER_ID + "", serverFormProp.getForm_user_id());
            value.put(SERVERFORM.ISNEW + "", serverFormProp.getForm_isnew());
            value.put(SERVERFORM.ACTION + "", serverFormProp.getForm_action());
            value.put(SERVERFORM.DATA + "", serverFormProp.getForm_data());
            int res = (int) db.insert(SERVERFORM.TB_SERVER_FORM + "", null, value);
            db.close();
            Log.d("Inserted NEW", res + "");
            return res;
        }

    }

    public int removeServerForm(String form_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(SERVERFORM.TB_SERVER_FORM + "", SERVERFORM.FORM_USER_ID + "=" + form_id, null);
        db.close();
        return res;
    }

    public LocalFormProp getLocalFormbyFormId(String formid) {
        SQLiteDatabase db_read = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + LOCALFORM.TB_LOCAL_FORM + " WHERE " + LOCALFORM.FORM_ID + "='" + formid + "'";
        Cursor c = db_read.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            LocalFormProp prop = new LocalFormProp();
            prop.setId(c.getInt((c.getColumnIndex(LOCALFORM.ID + ""))));
            prop.setForm_id(c.getString((c.getColumnIndex(LOCALFORM.FORM_ID + ""))));
            prop.setForm_type(c.getString((c.getColumnIndex(LOCALFORM.TYPE + ""))));
            prop.setForm_data(c.getString((c.getColumnIndex(LOCALFORM.DATA + ""))));
            return prop;
        }

        db_read.close();
        return null;
    }

    public int addLocalForm(LocalFormProp localFormProp) {

        SQLiteDatabase db_read = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + LOCALFORM.TB_LOCAL_FORM + " WHERE " + LOCALFORM.FORM_ID + "='" + localFormProp.getForm_id() + "'";
        Cursor c = db_read.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {

            int id = c.getInt((c.getColumnIndex(LOCALFORM.ID + "")));

            String where = LOCALFORM.ID + "=?";
            String[] whereArgs = new String[]{String.valueOf(id)};
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(LOCALFORM.DATA + "", localFormProp.getForm_data());
            int res = (int) db.update(LOCALFORM.TB_LOCAL_FORM + "", value, where, whereArgs);
            db.close();
            db_read.close();
            Log.d("Updated", res + "");
            return res;
        } else {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(LOCALFORM.TYPE + "", localFormProp.getForm_type());
            value.put(LOCALFORM.FORM_ID + "", localFormProp.getForm_id());
            value.put(LOCALFORM.DATA + "", localFormProp.getForm_data());
            int res = (int) db.insert(LOCALFORM.TB_LOCAL_FORM + "", null, value);
            db.close();
            db_read.close();
            Log.d("Inserted", res + "");
            return res;
        }
    }

    public int removeLocalForm(String form_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(LOCALFORM.TB_LOCAL_FORM + "", LOCALFORM.FORM_ID + "=" + form_id, null);
        db.close();
        return res;
    }

    public void insertSessionId(String sessionId) {
        Session.insertSessionId(db, sessionId);
    }

    public String getSessionId() {

        return Session.getSessionId(db);
    }


    private static class Session {
        private static final String tableName = "session";
        private static final String colId = "id";
        private static final String colSessionId = "sessionId";
        private static final String queryCreateTable = "create table " + tableName + "(" + colId + " text,"
                + colSessionId + " text)";

        private static void insertDefaultValues(SQLiteDatabase db) {
            ContentValues cv = new ContentValues();
            cv.put(colId, "1");
            cv.put(colSessionId, "-1");
            db.insert(tableName, null, cv);
        }

        private static void insertSessionId(SQLiteDatabase db, String sessionId) {
            ContentValues cv = new ContentValues();
            cv.put(colSessionId, sessionId);
            db.update(tableName, cv, colId + "='1'", null);
        }

        private static String getSessionId(SQLiteDatabase db) {
            Cursor c = db.rawQuery("select " + colSessionId + " from " + tableName + " where " + colId + "='1'", null);
            c.moveToFirst();
            if (c.getCount() < 1)
                return null;
            else
                return c.getString(0);
        }
    }
}
