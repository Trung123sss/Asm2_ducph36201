package com.example.asm2_ducph36201;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asm2_ducph36201.DAO.NguoidungDao;
import com.example.asm2_ducph36201.database.DBHelper;

public class GtActivity extends AppCompatActivity {
    NguoidungDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gt);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DBHelper dbHelper = new DBHelper(GtActivity.this);
                if (dbHelper.hasAccount()) {
                    // Đã có tài khoản
                    Intent intent = new Intent(GtActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    // Chưa có tài khoản
                    Intent intent = new Intent(GtActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            }
        },2000);
    }
}