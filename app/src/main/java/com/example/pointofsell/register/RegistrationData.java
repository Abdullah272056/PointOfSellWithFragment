package com.example.pointofsell.register;



public class RegistrationData {

   String companyName;
    String companyOwner;
    String email;
   String companyType;
    String description;
    String password;
    String address;
   String phone;

    public RegistrationData() {
    }

    public RegistrationData(String companyName, String companyOwner, String email, String companyType, String description, String password, String address, String phone) {
        this.companyName = companyName;
        this.companyOwner = companyOwner;
        this.email = email;
        this.companyType = companyType;
        this.description = description;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public void setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
