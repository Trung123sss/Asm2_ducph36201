package com.example.asm2_ducph36201.model;

public class CongViec {
    private int id;
    private String tieude,noidung,ngaydau,ngaycuoi;
    private int trangthai;

    public CongViec() {
    }

    public CongViec(int id, String tieude, String noidung, String ngaydau, String ngaycuoi, int trangthai) {
        this.id = id;
        this.tieude = tieude;
        this.noidung = noidung;
        this.ngaydau = ngaydau;
        this.ngaycuoi = ngaycuoi;
        this.trangthai = trangthai;
    }
    public CongViec(String tieude, String noidung, String ngaydau, String ngaycuoi){
        this.tieude=tieude;
        this.noidung=noidung;
        this.ngaydau=ngaydau;
        this.ngaycuoi=ngaycuoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgaydau() {
        return ngaydau;
    }

    public void setNgaydau(String ngaydau) {
        this.ngaydau = ngaydau;
    }

    public String getNgaycuoi() {
        return ngaycuoi;
    }

    public void setNgaycuoi(String ngaycuoi) {
        this.ngaycuoi = ngaycuoi;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
