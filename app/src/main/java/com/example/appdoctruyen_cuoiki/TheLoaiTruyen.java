package com.example.appdoctruyen_cuoiki;

public class TheLoaiTruyen {
    private String tenTheLoai;
    private String matheloai;
    private int hinhAnh;

    public TheLoaiTruyen() {
    }

    public TheLoaiTruyen(String tenTheLoai, String matheloai, int hinhAnh) {
        this.tenTheLoai = tenTheLoai;
        this.matheloai = matheloai;
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

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }
}
