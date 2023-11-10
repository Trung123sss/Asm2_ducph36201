package com.example.asm2_ducph36201.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asm2_ducph36201.database.DBHelper;
import com.example.asm2_ducph36201.model.CongViec;

import java.util.ArrayList;


public class CvDao {
    private static DBHelper dbHelper;

    public CvDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public static ArrayList<CongViec> selectall() {

        ArrayList<CongViec> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor1 = db.rawQuery("Select*from congviec", null);
            if (cursor1.getCount() > 0) {
                cursor1.moveToFirst();

                while (!cursor1.isAfterLast()) {


                    CongViec cv = new CongViec();
                    cv.setId(cursor1.getInt(0));
                    cv.setTieude(cursor1.getString(1));
                    cv.setNoidung(cursor1.getString(2));
                    cv.setNgaydau(cursor1.getString(3));
                    cv.setNgaycuoi(cursor1.getString(4));
                    cv.setTrangthai(cursor1.getInt(5));
                    list.add(cv);
                    cursor1.moveToNext();

                }
            }

        } catch (Exception ex) {
            Log.i(TAG, "loi", ex);
        }
        return list;
    }

    public boolean insertcv(CongViec cv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();//ghi dl
        ContentValues values = new ContentValues();// dua dl vao database

        values.put("tieude", cv.getTieude());
        values.put("noidung", cv.getNoidung());
        values.put("ngaydau", cv.getNgaydau());
        values.put("ngaycuoi", cv.getNgaycuoi());
        //neu add dl thanh cong thi tra ve gtri tuong ung voi so dong dc chen vao
        long row = db.insert("congviec", null, values);
        return (row > 0);
    }

    public static boolean updatecv(CongViec cv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();//ghi dl
        ContentValues values = new ContentValues();// dua dl vao database

        values.put("tieude", cv.getTieude());
        values.put("noidung", cv.getNoidung());
        values.put("ngaydau", cv.getNgaydau());
        values.put("ngaycuoi", cv.getNgaycuoi());


        //neu add dl thanh cong thi tra ve gtri tuong ung voi so dong dc chen vao
        long row = db.update("congviec", values, "id=?", new String[]{String.valueOf(cv.getId())});
        return (row > 0);
    }

    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("congviec", "id=?", new String[]{String.valueOf(id)});
        return (row > 0);
    }

}
