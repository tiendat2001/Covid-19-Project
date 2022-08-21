package com.example.code.models;

import java.sql.Date;

public class thongTinTest {
    private int nhanKhauId;
    private int thongTinTestId;
    private String Ten;
    private String hinhThucTest;
    private String ketQuaTest;
    private Date thoiGianTest;

    public thongTinTest(){};

    public thongTinTest(int Id, String Ten){
        this.nhanKhauId = Id;
        this.Ten = Ten;
    }
    public thongTinTest(String ten, String hinhThucTest, String ketQuaTest, Date thoiGianTest, int thongTinTestId, int nhanKhauId) {
        Ten = ten;
        this.hinhThucTest = hinhThucTest;
        this.ketQuaTest = ketQuaTest;
        this.thoiGianTest = thoiGianTest;
        this.thongTinTestId = thongTinTestId;
        this.nhanKhauId = nhanKhauId;
    }

    public int getThongTinTestId() {
        return thongTinTestId;
    }

    public int getNhanKhauId() {
        return nhanKhauId;
    }

    public String getKetQuaTest() {
        return ketQuaTest;
    }

    public String getTen() {
        return Ten;
    }

    public String getHinhThucTest() {
        return hinhThucTest;
    }

    public Date getThoiGianTest() {
        return thoiGianTest;
    }

}
