package com.example.code.models;

import com.example.code.database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminSession {
    private static AdminSession instance;
    private static int id;
    private static String userName;
    private static int privileges;

    private static String currentRole;

    public static void setCurrentRole(String currentRole) {
        AdminSession.currentRole = currentRole;
    }

    public AdminSession(String userName, int privileges, int id) {
        this.userName = userName;
        this.privileges = privileges;
        this.id = id;
    }

    public AdminSession(String userName, int id) {
        this.userName = userName;
        this.id = id;
    }

    public AdminSession(String userName) {
        this.userName = userName;
    }

    public AdminSession(int id) {
        this.id = id;
    }


    public AdminSession() {

    }

    public static AdminSession getInstance(String userName, int privileges, int id) {
        if(instance == null) {
            instance = new AdminSession(userName, privileges,id);
        }
        else {
            instance = null;
            instance = new AdminSession(userName, privileges,id);

        }
        return instance;
    }
    public static AdminSession getInstance(String userName) {
        if(instance == null) {
            instance = new AdminSession(userName);
        }
        else {
            instance = null;
            instance = new AdminSession(userName);
        }
        return instance;
    }

    public static AdminSession getInstanceId(int id) {
        if(instance == null) {
            instance = new AdminSession(id);
        }
        else {
            instance = null;
            instance = new AdminSession(id);
        }
        return instance;
    }

    public static String getUserName() {
        return userName;
    }

    public static int getId() {
        return id;
    }


    public static int getPrivileges() {
        return privileges;
    }

    public static void cleanUserSession() {
        userName = "";// or null
        privileges = 0;// or null
        id = 0;
    }

    public static AdminSession getInstance(String text, int ad_id) {
        if(instance == null) {
            instance = new AdminSession(text,ad_id);
        }
        else {
            instance = null;
            instance = new AdminSession(text,ad_id);

        }
        return instance;
    }
}
