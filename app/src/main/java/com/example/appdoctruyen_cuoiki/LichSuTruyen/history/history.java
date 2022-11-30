package com.example.appdoctruyen_cuoiki.LichSuTruyen.history;

public class history{
    private String ten;
    private Integer hinhAnh;

    public history(String ten, Integer hinhAnh) {
        this.ten = ten;
        this.hinhAnh = hinhAnh;
    }

    public history() {
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
