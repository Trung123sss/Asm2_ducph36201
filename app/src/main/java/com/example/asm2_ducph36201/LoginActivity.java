package com.example.asm2_ducph36201;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asm2_ducph36201.DAO.NguoidungDao;


public class LoginActivity extends AppCompatActivity {
    private NguoidungDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ánh xạ
        EditText edtUser=findViewById(R.id.edtUser);
        EditText edtPass=findViewById(R.id.edtPass);
        Button btnLogin=findViewById(R.id.btnLogin);
        TextView txtForgot=findViewById(R.id.txtForgot);
        TextView txtSignup=findViewById(R.id.txtSignUp);

        dao= new NguoidungDao(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=edtUser.getText().toString();
                String pass=edtPass.getText().toString();

                boolean check= dao.CheckLogin(user,pass);

                if(check){//sửa
                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,Navigation.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại.Ktra lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogForgot();

            }
        });
    }
    private void showDiaLogForgot(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_forgot,null);
        builder.setView(view);

        AlertDialog alertDialog=builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        //anh xa
        EditText edtEmail=view.findViewById(R.id.edtEmail);
        Button btnSend=view.findViewById(R.id.btnSend);
        Button btnCancel=view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=edtEmail.getText().toString();
                String matkhau=dao.Forgotpassword(email);
                Toast.makeText(LoginActivity.this, matkhau, Toast.LENGTH_SHORT).show();

            }
        });

    }
}