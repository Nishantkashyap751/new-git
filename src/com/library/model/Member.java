package com.library.model;

public class Member {
    private String memberId;
    private String name;
    private String email;

    // Constructor
    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    // Getters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    // Method to convert object to CSV line
    public String toCsvLine() {
        return memberId + "," + name + "," + email;
    }

    @Override
    public String toString() {
        return "ID: " + memberId + ", Name: " + name + ", Email: " + email;
    }
}