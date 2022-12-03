package com.example.appdoctruyen_cuoiki.LichSuTruyen.favBook;

public class FavFolder {

    private String idFolder;
    private String tenFolder;

    public FavFolder() {
    }

    public FavFolder(String idFolder, String tenFolder) {
        this.idFolder = idFolder;
        this.tenFolder = tenFolder;
    }

    public String getTenFolder() {
        return tenFolder;
    }

    public void setTenFolder(String tenFolder) {
        this.tenFolder = tenFolder;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }
}
