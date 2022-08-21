package com.example.code.models;

public class EditKhaiBaoYTeSession {
    private static EditNhanKhauSession instance;

    private static int nhankhau_id;


    public EditKhaiBaoYTeSession(Integer nhankhau_id) {

        this.nhankhau_id = nhankhau_id;
    }

    public EditKhaiBaoYTeSession() {

    }

    public static int getInstance() {

        return nhankhau_id;
    }

    public static EditNhanKhauSession getInstance(Integer nhankhau_id) {
        if(instance == null) instance = new EditNhanKhauSession(nhankhau_id);
        return instance;
    }


    public static void cleanSession() {
        instance = null;
        nhankhau_id = 0;// or null
    }
}
