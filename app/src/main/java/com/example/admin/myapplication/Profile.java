package com.example.admin.myapplication;

public class Profile {
    private final int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private int genderId;
    private String phoneNumber;
    private String email;

    public Profile(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Profile setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Profile setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Profile setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public int getGenderId() {
        return genderId;
    }

    public Profile setGenderId(int genderId) {
        this.genderId = genderId;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Profile setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Profile setEmail(String email) {
        this.email = email;
        return this;
    }
}
