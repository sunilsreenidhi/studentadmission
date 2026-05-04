package com.onesports.models;

public class userData {

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

    public userData(String initials, String fullName, String surname, String email,
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
}
    

