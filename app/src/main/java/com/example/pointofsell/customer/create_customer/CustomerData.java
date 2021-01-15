package com.example.pointofsell.customer.create_customer;

public class CustomerData {
        private String name;
        private String phone;
        private String email;
        private String address;

    public CustomerData() {
    }
    public CustomerData(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    public CustomerData(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
