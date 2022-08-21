package com.example.code.models;

import java.util.Date;

public class ThongTinCachLy {
    private int nhankhau_id;
    private Date thoigianbatdau;
    private int thoigiancachli;
    private int thongtincachli_id;

    private int diadiemcachli_id;
    private String diadiem;

    private String ten;
    private java.sql.Date ngaysinh;
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


    public ThongTinCachLy(int nhankhau_id, Date thoigianbatdau, int thoigiancachli, int thongtincachli_id, int diadiemcachli_id, String diadiem, String ten) {
        this.nhankhau_id = nhankhau_id;
        this.thoigianbatdau = thoigianbatdau;
        this.thoigiancachli = thoigiancachli;
        this.thongtincachli_id = thongtincachli_id;
        this.diadiemcachli_id = diadiemcachli_id;
        this.diadiem = diadiem;
        this.ten = ten;
    }

    public ThongTinCachLy() {
    }

    public ThongTinCachLy(String diadiem, int diadiemcachli_id){
        this.diadiem = diadiem;
        this.diadiemcachli_id = diadiemcachli_id;
    }
    public ThongTinCachLy(int nhankhau_id,  String ten){
        this.nhankhau_id = nhankhau_id;
        this.ten=ten;

    }
    public ThongTinCachLy( int thongtincachli_id, String ten, Date thoigianbatdau, int thoigiancachli, String diadiem) {
        this.thoigianbatdau = thoigianbatdau;
        this.thoigiancachli = thoigiancachli;
        this.thongtincachli_id = thongtincachli_id;
        this.diadiem = diadiem;
        this.ten = ten;
    }

    public ThongTinCachLy( int thongtincachli_id, String ten,int nhankhau_id, Date thoigianbatdau, int thoigiancachli, String diadiem) {
        this.thoigianbatdau = thoigianbatdau;
        this.nhankhau_id = nhankhau_id;
        this.thoigiancachli = thoigiancachli;
        this.thongtincachli_id = thongtincachli_id;
        this.diadiem = diadiem;
        this.ten = ten;
    }

    public int getDiadiemcachli_id() {
        return diadiemcachli_id;
    }

    public void setDiadiemcachli_id(int diadiemcachli_id) {
        this.diadiemcachli_id = diadiemcachli_id;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }


    public int getNhankhau_id() {
        return nhankhau_id;
    }

    public void setNhankhau_id(int nhankhau_id) {
        this.nhankhau_id = nhankhau_id;
    }

    public Date getThoigianbatdau() {
        return thoigianbatdau;
    }

    public void setThoigianbatdau(Date thoigianbatdau) {
        this.thoigianbatdau = thoigianbatdau;
    }

    public int getThoigiancachli() {
        return thoigiancachli;
    }

    public void setThoigiancachli(int thoigiancachli) {
        this.thoigiancachli = thoigiancachli;
    }

    public int getThongtincachli_id() {
        return thongtincachli_id;
    }

    public void setThongtincachli_id(int thongtincachli_id) {
        this.thongtincachli_id = thongtincachli_id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public java.sql.Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(java.sql.Date ngaysinh) {
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

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    @Override
    public String toString(){

        return "nhankhau_id: " + getNhankhau_id() + "\n" + "diadiemcachli_id: " + getDiadiemcachli_id();
    }
}
