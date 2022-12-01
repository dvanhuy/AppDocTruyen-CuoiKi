package com.example.appdoctruyen_cuoiki;

public class Truyen{
    private String mota,tacgia,tentruyen,theloai;
    private String soChuong;

    public Truyen(String mota, String tacgia, String tentruyen, String theloai, String soChuong) {
        this.mota = mota;
        this.tacgia = tacgia;
        this.tentruyen = tentruyen;
        this.theloai = theloai;
        this.soChuong = soChuong;
    }

    public Truyen() {
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getSoChuong() {
        return soChuong;
    }

    public void setSoChuong(String soChuong) {
        this.soChuong = soChuong;
    }
}
