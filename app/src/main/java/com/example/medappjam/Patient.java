package com.example.medappjam;



public class Patient {
    private int patientId;
    private String username;
    private String password;
    //private ArrayList<String> profiles;
    //private ArrayList<Integer> providerIds;


    public Patient(int patientId, String username, String password) {
        this.patientId = patientId;
        this.username = username;
        this.password = password;
    }

    public Patient(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /** not used yet because profiles are added separately than account creation
    public Patient(String username, String password, ArrayList<String> profiles) {
        this.username = username;
        this.password = password;
    }

    public Patient(int patientId, String username, String password, ArrayList<String> profiles) {
        this.patientId = patientId;
        this.username = username;
        this.password = password;
    }
     */

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
    public void setProfiles(String profile) {
        profiles.add(profile);
    }

    public void setProviderIds(int providerId) {
        providerIds.add(providerId);
    }
     */
}
