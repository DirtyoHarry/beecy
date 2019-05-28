package com.example.baik.Tables;

public class users {
    String bike;
    String email;
    String uid;

    public users(String id, String email, String uid) {
        this.bike = id;
        this.email = email;
        uid = uid;
    }
    public users() {

    }

    public String getId() {
        return bike;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public void setId(String bike) {
        this.bike = bike;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUid(String uid) {
        uid = uid;
    }
}
