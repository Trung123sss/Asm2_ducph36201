package com.example.asm2_ducph36201.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context,"AND102",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {//tạo table
        String tb_us="CREATE TABLE NGUOIDUNG(tendangnhap TEXT PRIMARY KEY,matkhau TEXT,hoten TEXT, )";
        db.execSQL(tb_us);

        String tb_cv="create table congviec(id integer primary key autoincrement,"+
                "tieude text,"+
                "noidung text,"+
                "ngaydau text,"+
                "ngaycuoi text," +
                "trangthai integer)";
        db.execSQL(tb_cv);

        //chen dl
//        String data1 ="INSERT INTO NGUOIDUNG VALUES ('giapvx','123','Giap Nè'),('anhdt','321','Ánh Nè')";
//        db.execSQL(data1);

        String data2="insert into congviec values(1,'Java co ban','java','2001/04/05','2001/08/09',1),"+
                "(2,'Android co ban','android','2001/04/05','2001/08/09',2),"+
                "(3,'Java nang cao','java','2001/04/05','2001/08/09',1)";
        db.execSQL(data2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");

            db.execSQL("DROP TABLE IF EXISTS congviec");
            onCreate(db);

        }
    }
    public boolean hasAccount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM NGUOIDUNG" ;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
}
