package com.example.asm2_ducph36201;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm2_ducph36201.DAO.CvDao;
import com.example.asm2_ducph36201.adapter.Congviecadapter;
import com.example.asm2_ducph36201.model.CongViec;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListFrg extends Fragment {
    CvDao dao;
    FloatingActionButton fltadd;
    Congviecadapter adapter;
    CongViec congviec;
    RecyclerView rcView;

    private ArrayList<CongViec> listsp = new ArrayList<CongViec>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_frg,container,false);

        rcView =view.findViewById(R.id.rcv);
        fltadd=view.findViewById(R.id.btnadd);

        dao= new CvDao(getActivity());
        listsp=dao.selectall();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rcView.setLayoutManager(manager);

        adapter = new Congviecadapter(getActivity(),listsp);
        rcView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    public void add(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.item_add,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edttieudea = view.findViewById(R.id.edttieude);
        EditText edtnoidunga = view.findViewById(R.id.edtnoidung);
        EditText edtngaydaua = view.findViewById(R.id.edtngaydaua);
        EditText edtngaycuoia = view.findViewById(R.id.edtngaycuoia);


        Button btnadd = view.findViewById(R.id.btnsm);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tieude = edttieudea.getText().toString();
                String noidung = edtnoidunga.getText().toString();
                String ngaydau = edtngaydaua.getText().toString();
                String ngaycuoi = edtngaycuoia.getText().toString();
                congviec = new CongViec(tieude, noidung, ngaydau, ngaycuoi);//tao doi tuong
                if (dao.insertcv(congviec)) {
                    listsp.clear();//xoa toan bo dl trong list
                    listsp.addAll(dao.selectall());//lay toan bo dl trong database
                    adapter.notifyDataSetChanged();//load laij dl trong adapter
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getActivity(), "Them không thanh cong", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}