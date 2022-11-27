package com.example.appdoctruyen_cuoiki;

public class Truyen{
    private String ten;
    private Integer soChap, hinhAnh;

    public Truyen(String ten, Integer soChap, Integer hinhAnh) {
        this.ten = ten;
        this.soChap = soChap;
        this.hinhAnh = hinhAnh;
    }

    public Truyen() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getSoChap() {
        return soChap;
    }

    public void setSoChap(Integer soChap) {
        this.soChap = soChap;
    }

    public Integer getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(Integer hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
