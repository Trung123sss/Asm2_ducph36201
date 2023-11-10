package com.example.asm2_ducph36201.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm2_ducph36201.database.DBHelper;

public class NguoidungDao {
    private DBHelper dbHelper;

    public NguoidungDao(Context context){
        dbHelper= new DBHelper(context);
    }
    //login
    public boolean CheckLogin(String username,String password){
        SQLiteDatabase sqLiteDatabase= dbHelper.getReadableDatabase();

        Cursor cursor= sqLiteDatabase.rawQuery("SELECT *FROM NGUOIDUNG WHERE tendangnhap=? AND matkhau=? ",new String[]{username,password});
        return cursor.getCount() > 0;
    }

    //register
    public boolean Register(String username, String password, String hoten, String Email){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tendangnhap",username);
        contentValues.put("matkhau",password);
        contentValues.put("hoten",hoten);


        long check=sqLiteDatabase.insert("NGUOIDUNG",null,contentValues);
        if(check!=-1){
            return true;
        }
        return false;
    }

    //forgot
    public String Forgotpassword(String email){
        SQLiteDatabase sqLiteDatabase =dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT matkhau FROM NGUOIDUNG WHERE tendangnhap=?",new String[]{email});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }else {
            return "";
        }

        //1 4  5 7 2

    }
}
