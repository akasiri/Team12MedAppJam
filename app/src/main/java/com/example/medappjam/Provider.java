package com.example.medappjam;


public class Provider {
    private int providerId;
    private String name;
    private String phoneNumber;

    public Provider(int providerId, String name, String phoneNumber) {
        this.providerId = providerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Provider(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
