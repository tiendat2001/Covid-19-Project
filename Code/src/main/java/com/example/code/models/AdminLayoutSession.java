package com.example.code.models;

public class AdminLayoutSession {
    private static AdminLayoutSession instance;

    private static int screen_id;


    public AdminLayoutSession(Integer screen_id) {

        this.screen_id = screen_id;
    }

    public static int getInstance() {

        return screen_id;
    }

    public static AdminLayoutSession getInstance(Integer screen_id) {
        if(instance == null) instance = new AdminLayoutSession(screen_id);
        return instance;
    }


    public static void cleanSession() {
        instance = null;
        screen_id = 0;// or null
    }
}
