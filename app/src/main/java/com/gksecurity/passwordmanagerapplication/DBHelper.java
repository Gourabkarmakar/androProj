package com.gksecurity.passwordmanagerapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Userdetails(domainname TEXT primary key, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS Userdetails");
    }

    public Boolean insertdata(String domainname, String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("domainname",domainname);
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = DB.insert("Userdetails",null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return  true;
        }
    }

    public Boolean updatedata(String domainname, String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE domainname = ?", new String[]{domainname});
        if (cursor.getCount()>0){

            long result = DB.update("Userdetails", contentValues,"domainname=?", new String[]{domainname});
            if (result == -1){
                return false;
            }
            else {
                return  true;
            }
        }else {
            return  false;
        }
    }

    public Boolean deletedata(String domainname){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE domainname = ?", new String[]{domainname});
        if (cursor.getCount()>0){

            long result = DB.delete("Userdetails", "domainname=?", new String[]{domainname});
            if (result == -1){
                return false;
            }
            else {
                return  true;
            }
        }else {
            return  false;
        }
    }

    public Cursor viewdata(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails",null);
        return cursor;
    }
}
