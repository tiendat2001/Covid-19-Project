package com.example.code.models;

import java.sql.Date;

public class NhanKhau {
    private int nhankhau_id;
    private String ten;
    private Date ngaysinh;
    private String gioitinh;
    private String CMND;
    private int diachi_id;
    private String SDT;
    private int datest;
    private String tinh;
    private String quan;
    private String phuong;
    private String soNha;
    private String diachi;
    public NhanKhau() {

    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
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

    public int getNhankhau_id() {
        return nhankhau_id;
    }

    public void setNhankhau_id(int nhankhau_id) {
        this.nhankhau_id = nhankhau_id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public int getDiachi_id() {
        return diachi_id;
    }

    public void setDiachi_id(int diachi_id) {
        this.diachi_id = diachi_id;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getDatest() {
        return datest;
    }

    public void setDatest(int datest) {
        this.datest = datest;
    }
    @Override
    public String toString(){
        return this.getTen();
    }
}
