package com.onesports.models;

public class UserData {

    private String initials;
    private String fullName;
    private String surname;
    private String email;
    private String mobile;
    private String altCode;
    private String altMobile;
    private String state;
    private String city;
    private String course;
    private String spec1;
    private String spec2;
    private String password;

     // Step1 - Personal Details
    private String photoPath;
    private String aadhaarFilePath;
    private String dob;
    private String gender;
    private String aadhaarNumber;
    private String bloodGroup;
    private String admissionType;
    private String transportRequired;
    private String hostelRequired;

    // Parent Details
    private String fatherName;
    private String fatherEmail;
    private String fatherMobile;

    private String motherName;
    private String motherEmail;
    private String motherMobile;

    //     public UserData() {
    // }   

    public UserData(String initials, String fullName, String surname, String email,
                    String mobile, String altCode, String altMobile,
                    String state, String city, String course,
                    String spec1, String spec2, String password) {

        this.initials = initials;
        this.fullName = fullName;
        this.surname = surname;
        this.email = email;
        this.mobile = mobile;
        this.altCode = altCode;
        this.altMobile = altMobile;
        this.state = state;
        this.city = city;
        this.course = course;
        this.spec1 = spec1;
        this.spec2 = spec2;
        this.password = password;
    }

    // getters (generate using IDE)
    public String getInitials() 
    {
         return initials; 
    }
    public String getFullName()
    {
         return fullName; 
 }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getAltCode() { return altCode; }
    public String getAltMobile() { return altMobile; }
    public String getState() { return state; }
    public String getCity() { return city; }
    public String getCourse() { return course; }
    public String getSpec1() { return spec1; }
    public String getSpec2() { return spec2; }
    public String getPassword() { return password; }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getAadhaarFilePath() {
        return aadhaarFilePath;
    }

    public void setAadhaarFilePath(String aadhaarFilePath) {
        this.aadhaarFilePath = aadhaarFilePath;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getTransportRequired() {
        return transportRequired;
    }

    public void setTransportRequired(String transportRequired) {
        this.transportRequired = transportRequired;
    }

    public String getHostelRequired() {
        return hostelRequired;
    }

    public void setHostelRequired(String hostelRequired) {
        this.hostelRequired = hostelRequired;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    
}
    

