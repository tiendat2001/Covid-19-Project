package com.example.code.models;

public class DiaChi {
    private int diachi_id;
    private String tinh;
    private String quan;
    private String phuong;
    private String soNha;

    public DiaChi(int diachi_id, String tinh, String quan, String phuong, String soNha) {
        this.diachi_id = diachi_id;
        this.tinh = tinh;
        this.quan = quan;
        this.phuong = phuong;
        this.soNha = soNha;
    }

    public DiaChi() {

    }

    public int getDiachi_id() {
        return diachi_id;
    }

    public void setDiachi_id(int diachi_id) {
        this.diachi_id = diachi_id;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getPhuong() {
        return phuong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    @Override
    public String toString(){
        return this.getSoNha() + ", " + this.getPhuong() + ", " + this.getQuan() + ", " + this.getTinh();
    }
}
