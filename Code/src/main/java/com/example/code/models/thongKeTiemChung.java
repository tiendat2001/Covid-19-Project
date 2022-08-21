package com.example.code.models;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.scene.chart.PieChart;

import java.sql.Date;
public class thongKeTiemChung {
    private static thongKeTiemChung instance;
    private int nhankhau_id;
    private int thongke_id ;
    private String ten;
    private int datiem;
    private int somuitiem;
    private String loaivacxinlan1;
    private String loaivacxinlan2;
    private Date thoigiantiemlan1;
    private Date thoigiantiemlan2;

    public thongKeTiemChung(int thongke_id) {
        this.thongke_id = thongke_id;
    }

   public thongKeTiemChung(){}

    public thongKeTiemChung(int nhankhau_id, int thongke_id, String ten, int datiem, int somuitiem, String loaivacxinlan1, String loaivacxinlan2, Date thoigiantiemlan1, Date thoigiantiemlan2) {
        this.nhankhau_id = nhankhau_id;
        this.thongke_id = thongke_id;
        this.ten = ten;
        this.datiem = datiem;
        this.somuitiem = somuitiem;
        this.loaivacxinlan1 = loaivacxinlan1;
        this.loaivacxinlan2 = loaivacxinlan2;
        this.thoigiantiemlan1 = thoigiantiemlan1;
        this.thoigiantiemlan2 = thoigiantiemlan2;
    }

    public thongKeTiemChung(int nhankhau_id, int thongke_id, int datiem, int somuitiem, String loaivacxinlan1, String loaivacxinlan2, Date thoigiantiemlan1, Date thoigiantiemlan2) {
        this.nhankhau_id = nhankhau_id;
        this.thongke_id = thongke_id;
        this.datiem = datiem;
        this.somuitiem = somuitiem;
        this.loaivacxinlan1 = loaivacxinlan1;
        this.loaivacxinlan2 = loaivacxinlan2;
        this.thoigiantiemlan1 = thoigiantiemlan1;
        this.thoigiantiemlan2 = thoigiantiemlan2;
    }

    public int getNhankhau_id() {
        return nhankhau_id;
    }

    public void setNhankhau_id(int nhankhau_id) {
        this.nhankhau_id = nhankhau_id;
    }

    public int getThongke_id() {
        return thongke_id;
    }

    public void setThongke_id(int thongke_id) {
        this.thongke_id = thongke_id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getDatiem() {
        return datiem;
    }

    public void setDatiem(int datiem) {
        this.datiem = datiem;
    }

    public int getSomuitiem() {
        return somuitiem;
    }

    public void setSomuitiem(int somuitiem) {
        this.somuitiem = somuitiem;
    }

    public String getLoaivacxinlan1() {
        return loaivacxinlan1;
    }

    public void setLoaivacxinlan1(String loaivacxinlan1) {
        this.loaivacxinlan1 = loaivacxinlan1;
    }

    public String getLoaivacxinlan2() {
        return loaivacxinlan2;
    }

    public void setLoaivacxinlan2(String loaivacxinlan2) {
        this.loaivacxinlan2 = loaivacxinlan2;
    }

    public Date getThoigiantiemlan1() {
        return thoigiantiemlan1;
    }

    public void setThoigiantiemlan1(Date thoigiantiemlan1) {
        this.thoigiantiemlan1 = thoigiantiemlan1;
    }

    public Date getThoigiantiemlan2() {
        return thoigiantiemlan2;
    }

    public void setThoigiantiemlan2(Date thoigiantiemlan2) {
        this.thoigiantiemlan2 = thoigiantiemlan2;
    }

    public ObservableValue<String> getCheckTiem(){
        if(this.datiem == 1){
            ObservableValue<String> string1 = new ObservableValueBase<String>() {

                @Override
                public String getValue() {
                    return "Đã tiêm";
                }
            };
            return string1;
        }
        if(this.datiem == 0){
            ObservableValue<String> string2 = new ObservableValueBase<String>() {
                @Override
                public String getValue() {
                    return "Chưa tiêm";
                }
            };
            return string2;
        }
        return null;
    }


}
