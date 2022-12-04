package com.example.appdoctruyen_cuoiki;

public class Truyen{
    private String key;
    private String mota,tacgia,tentruyen, phathanh, anban;
    private String soChuong, image;

    public Truyen() {
    }

    public Truyen(String mota, String tacgia, String tentruyen, String soChuong) {
        this.mota = mota;
        this.tacgia = tacgia;
        this.tentruyen = tentruyen;
        this.soChuong = soChuong;
    }

    public Truyen(String mota, String tacgia, String tentruyen, String soChuong, String image) {
        this.mota = mota;
        this.tacgia = tacgia;
        this.tentruyen = tentruyen;
        this.soChuong = soChuong;
        this.image = image;
    }

    public Truyen(String key, String mota, String tacgia, String tentruyen, String phathanh, String anban, String soChuong, String image) {
        this.key = key;
        this.mota = mota;
        this.tacgia = tacgia;
        this.tentruyen = tentruyen;
        this.phathanh = phathanh;
        this.anban = anban;
        this.soChuong = soChuong;
        this.image = image;
    }

    public String getPhathanh() {
        return phathanh;
    }

    public void setPhathanh(String phathanh) {
        this.phathanh = phathanh;
    }

    public String getAnban() {
        return anban;
    }

    public void setAnban(String anban) {
        this.anban = anban;
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

    public String getSoChuong() {
        return soChuong;
    }

    public void setSoChuong(String soChuong) {
        this.soChuong = soChuong;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
