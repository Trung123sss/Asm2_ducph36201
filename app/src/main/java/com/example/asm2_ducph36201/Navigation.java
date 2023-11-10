package com.example.asm2_ducph36201;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class Navigation extends AppCompatActivity {
 DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nvgview;
    Fragment frag_01;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        drawerLayout = findViewById(R.id.drawable);
        toolbar = findViewById(R.id.toolbar);
        nvgview= findViewById(R.id.nvgview);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Asignment");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        frag_01 = new ListFrg();

        getSupportFragmentManager().beginTransaction().add(R.id.frm,frag_01).commit();


        nvgview.setItemIconTintList(null);
        nvgview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {




                if(item.getItemId()==R.id.dangxuat){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Navigation.this);
                    builder.setTitle("Cảnh báo");
                    builder.setMessage("Bạn có muốn đăng xuất?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Navigation.this,LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(Navigation.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();




                }else if (item.getItemId()==R.id.qlsp){
                    ListFrg listFrg= new ListFrg();
                    replace(listFrg);

                }else if(item.getItemId()==R.id.gioithieu){
                    Frg2 frg2=new Frg2();
                    replace(frg2);

                }else if (item.getItemId() == R.id.caidat){
                    Fragment frg3= new Caidat();
                    replace(frg3);
                }
                return true;
            }
        });


    }
    public void replace(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frm,fragment).commit();
        drawerLayout.close();


    }
}