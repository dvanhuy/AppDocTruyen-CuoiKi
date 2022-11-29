package com.example.appdoctruyen_cuoiki;

public class TheLoaiTruyen {
    private String tenTheLoai;
    private int hinhAnh;

    public TheLoaiTruyen() {
    }

    public TheLoaiTruyen(String tenTheLoai, int hinhAnh) {
        this.tenTheLoai = tenTheLoai;
        this.hinhAnh = hinhAnh;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
