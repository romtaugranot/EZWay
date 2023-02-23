package com.example.ezway;

import java.util.Date;

public class User {

    private String id;
    private Date dateOfBirth;
    private String gender;
    private String username;
    private String email;

    public User(String id, Date dateOfBirth, String gender, String username, String email) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.username = username;
        this.email = email;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
