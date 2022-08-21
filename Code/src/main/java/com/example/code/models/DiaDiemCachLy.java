package com.example.code.models;

public class DiaDiemCachLy {
    private int diadiemcachli_id;
    private String diadiem;

    public DiaDiemCachLy(){

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

    public DiaDiemCachLy(int diadiemcachli_id, String diadiem) {
        this.diadiemcachli_id = diadiemcachli_id;
        this.diadiem = diadiem;
    }
    @Override
    public String toString(){
        return this.getDiadiem();
    }
}
