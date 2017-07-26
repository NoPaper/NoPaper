package com.planb.nopaper.support.account;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.androidquery.AQuery;
import com.planb.nopaper.support.database.DBHelper;

/**
 * Created by dsm2016 on 2017-07-26.
 */

public class AccountManager {
    public static SQLiteDatabase getReadableDatabase(Context context) {
        DBHelper helper = DBHelper.getInstance(context, "check.db", null, 1);
        return helper.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        DBHelper helper = DBHelper.getInstance(context, "check.db", null, 1);
        return helper.getWritableDatabase();
    }

    public static String getId(Context context) {
        SQLiteDatabase db = getReadableDatabase(context);

        Cursor cursor = db.rawQuery("SELECT * FROM `checker`", null);
        cursor.moveToFirst();

        return cursor.getString(0);
    }

    public static void setId(Context context, String id) {
        SQLiteDatabase db = getWritableDatabase(context);

        SQLiteStatement stmt = db.compileStatement("UPDATE `checker` SET id=?");
        stmt.bindString(1, id);
        stmt.executeUpdateDelete();
    }

    public static boolean isLogined(Context context) {
        if(getId(context) != null) {
            // Is not null : Is logined
            return true;
        } else {
            return false;
        }
    }

    public static String[] getWishList(Context context) {
        SQLiteDatabase db = getReadableDatabase(context);

        Cursor cursor = db.rawQuery("SELECT * FROM `wish_list`", null);
        String[] wishList = new String[cursor.getCount()];
        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            wishList[i] = cursor.getString(0);
        }

        return wishList;
    }

    public static void addWishList(Context context, String id) {
        SQLiteDatabase db = getWritableDatabase(context);
        SQLiteStatement stmt = db.compileStatement("INSERT INTO `wish_list` VALUES(?)");
        stmt.bindString(1, id);
        stmt.executeInsert();
    }

    public static String[] getRecentFiles(Context context) {
        SQLiteDatabase db = getReadableDatabase(context);

        Cursor cursor = db.rawQuery("SELECT * FROM `recent_file`", null);
        String[] wishList = new String[cursor.getCount()];
        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            wishList[i] = cursor.getString(0);
        }

        return wishList;
    }

    public static void addRecentFiles(Context context, String id) {
        SQLiteDatabase db = getWritableDatabase(context);
        SQLiteStatement stmt = db.compileStatement("INSERT INTO `recent_file` VALUES(?)");
        stmt.bindString(0, id);
        stmt.executeInsert();
    }
}
