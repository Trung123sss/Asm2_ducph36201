package com.example.asm2_ducph36201.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm2_ducph36201.DAO.CvDao;
import com.example.asm2_ducph36201.R;
import com.example.asm2_ducph36201.model.CongViec;

import java.util.ArrayList;
import java.util.Calendar;

public class Congviecadapter extends RecyclerView.Adapter<Congviecadapter.ViewHolder>{
    private final Context context;
    private final ArrayList<CongViec> list;
    CvDao dao;
    public Congviecadapter(Context context, ArrayList<CongViec> list) {
        this.context = context;
        this.list = list;
        dao=new CvDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_cv,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttieude.setText(list.get(position).getTieude());
        holder.txtnoidung.setText(list.get(position).getNoidung());
        holder.txtngaydau.setText(list.get(position).getNgaydau());
        holder.txtngaycuoi.setText(list.get(position).getNgaycuoi());
        CongViec congviec=list.get(position);
        if (list.get(position).getTrangthai()==1){
            holder.chkcv.setChecked(true);

        }else{
            holder.chkcv.setChecked(false);
        }
        CongViec cv=list.get(position);
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.canhbao);
                builder.setMessage("Bạn có muốn xóa ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dao.delete(congviec.getId())){
                            list.clear();
                            list.addAll(dao.selectall());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
                builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Không", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog= builder.create();
                dialog.show();
            }
        });
        CongViec cv1=list.get(position);
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {opendialog(cv);
            }




        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttieude,txtnoidung,txtngaydau,txtngaycuoi,txttrangthai;

        ImageButton btnupdate, btndelete;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txttieude = itemView.findViewById(R.id.txttieude);
            txtnoidung = itemView.findViewById(R.id.txtnoidung);
            txtngaydau = itemView.findViewById(R.id.txtngaydau);
            txtngaycuoi = itemView.findViewById(R.id.txtngaycuoi);
            btnupdate = itemView.findViewById(R.id.btnupdate);
            btndelete = itemView.findViewById(R.id.btndelete);


        }
    }
    private void opendialog(CongViec cv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ud, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        //anh xa

        EditText edttieude = view.findViewById(R.id.edttieudeu);
        EditText edtnoidung = view.findViewById(R.id.edtnoidungu);
        EditText edtngaydau = view.findViewById(R.id.edtngaydauu);
        EditText edtngaycuoi = view.findViewById(R.id.edtngaycuoiu);

        Button btnsumu = view.findViewById(R.id.btnsmu);
        //
        edttieude.setText(cv.getTieude());
        edtnoidung.setText(cv.getNoidung());
        edtngaydau.setText(cv.getNgaydau());
        edtngaycuoi.setText(cv.getNgaycuoi());

        edtngaydau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar lich=Calendar.getInstance();//tạo đối tượng để lấy ngày giờ hiện tại
                int year=lich.get(Calendar.YEAR);
                int month=lich.get(Calendar.MONTH);
                int day=lich.get(Calendar.DAY_OF_MONTH);
                //Tạo đối tượng DatePickerDialog và show nó
                DatePickerDialog datedg=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtngaydau.setText(year+"/"+month+"/"+dayOfMonth);
                    }
                },year,month,day);
                datedg.show();
            }
        });
        edtngaycuoi.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View v) {
                Calendar lich=Calendar.getInstance();//tạo đối tượng để lấy ngày giờ hiện tại
                int year=lich.get(Calendar.YEAR);
                int month=lich.get(Calendar.MONTH);
                int day=lich.get(Calendar.DAY_OF_MONTH);
                //Tạo đối tượng DatePickerDialog và show nó
                DatePickerDialog datedg=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtngaycuoi.setText(year+"/"+month+"/"+dayOfMonth);
                    }
                },year,month,day);
                datedg.show();
            }
        });
        btnsumu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.setTieude(edttieude.getText().toString());
                cv.setNoidung(edtnoidung.getText().toString());
                cv.setNgaydau(edtngaydau.getText().toString());
                cv.setNgaycuoi(edtngaycuoi.getText().toString());
                if(CvDao.updatecv(cv)){
                    list.clear();
                    list.addAll(CvDao.selectall());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "update fall", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
