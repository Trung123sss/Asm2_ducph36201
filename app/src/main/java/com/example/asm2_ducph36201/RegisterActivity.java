package com.example.asm2_ducph36201;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asm2_ducph36201.DAO.NguoidungDao;


public class RegisterActivity extends AppCompatActivity {
    private NguoidungDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //anhs xaj
        EditText edtId=findViewById(R.id.edtId);
        EditText edtUser=findViewById(R.id.edtUser);
        EditText edtPass=findViewById(R.id.edtPass);
        EditText edtRe_Pass=findViewById(R.id.edtRe_Pass);
        EditText edtEmail=findViewById(R.id.edtEmail);
        EditText edtFullname=findViewById(R.id.edtFullname);
        Button btnRegister=findViewById(R.id.btnRegister);
        Button btnGoBack=findViewById(R.id.btnGoBack);

        dao= new NguoidungDao(this);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id=edtId.getText().toString();
                String user=edtUser.getText().toString();
                String pass=edtPass.getText().toString();
                String rePass=edtRe_Pass.getText().toString();
                String Email=edtEmail.getText().toString();
                String fullname=edtFullname.getText().toString();

                if(!pass.equals(rePass)){
                    Toast.makeText(RegisterActivity.this, "Nhap 2 mat khau khong trung nhau", Toast.LENGTH_SHORT).show();
                }else{
                    boolean check=dao.Register(user,pass,fullname,Email);
                    if (check){
                        Toast.makeText(RegisterActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}