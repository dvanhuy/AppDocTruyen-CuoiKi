package com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook;

public class favBook{
    private String ten;
    private Integer hinhAnh;

    public favBook(String ten, Integer hinhAnh) {
        this.ten = ten;
        this.hinhAnh = hinhAnh;
    }

    public favBook() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(Integer hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
