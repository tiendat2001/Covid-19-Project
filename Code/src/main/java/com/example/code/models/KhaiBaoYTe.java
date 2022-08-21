package com.example.code.models;

public class KhaiBaoYTe {
    private int nhankhau_id;
    private int khaibaoyte_id;
    private String ten;
    private String soNha;
    private String quan;
    private String phuong;
    private String codiquavungdich;
    private String cotrieuchung;
    private String tiepxucnguoimaccovid;
    private String tiepxucnguoituvungdich;
    private String tiepxucnguoicobieuhien;

    public KhaiBaoYTe(int khaibaoyte_id,String ten, String soNha, String quan, String phuong, String codiquavungdich, String cotrieuchung, String tiepxucnguoimaccovid, String tiepxucnguoituvungdich, String tiepxucnguoicobieuhien) {
        this.khaibaoyte_id=khaibaoyte_id;
        this.ten = ten;
        this.soNha = soNha;
        this.quan = quan;
        this.phuong = phuong;
        this.codiquavungdich = codiquavungdich;
        this.cotrieuchung = cotrieuchung;
        this.tiepxucnguoimaccovid = tiepxucnguoimaccovid;
        this.tiepxucnguoituvungdich = tiepxucnguoituvungdich;
        this.tiepxucnguoicobieuhien = tiepxucnguoicobieuhien;
    }


    public int getNhankhau_id() {
        return nhankhau_id;
    }

    public void setNhankhau_id(int nhankhau_id) {
        this.nhankhau_id = nhankhau_id;
    }

    public int getKhaibaoyte_id() {
        return khaibaoyte_id;
    }

    public void setKhaibaoyte_id(int khaibaoyte_id) {
        this.khaibaoyte_id = khaibaoyte_id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
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

    public String getCodiquavungdich() {
        return codiquavungdich;
    }

    public void setCodiquavungdich(String codiquavungdich) {
        this.codiquavungdich = codiquavungdich;
    }

    public String getCotrieuchung() {
        return cotrieuchung;
    }

    public void setCotrieuchung(String cotrieuchung) {
        this.cotrieuchung = cotrieuchung;
    }

    public String getTiepxucnguoimaccovid() {
        return tiepxucnguoimaccovid;
    }

    public void setTiepxucnguoimaccovid(String tiepxucnguoimaccovid) {
        this.tiepxucnguoimaccovid = tiepxucnguoimaccovid;
    }

    public String getTiepxucnguoituvungdich() {
        return tiepxucnguoituvungdich;
    }

    public void setTiepxucnguoituvungdich(String tiepxucnguoituvungdich) {
        this.tiepxucnguoituvungdich = tiepxucnguoituvungdich;
    }

    public String getTiepxucnguoicobieuhien() {
        return tiepxucnguoicobieuhien;
    }

    public void setTiepxucnguoicobieuhien(String tiepxucnguoicobieuhien) {
        this.tiepxucnguoicobieuhien = tiepxucnguoicobieuhien;
    }
}
