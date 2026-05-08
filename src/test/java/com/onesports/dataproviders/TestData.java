package com.onesports.dataproviders;

import org.testng.annotations.DataProvider;

import com.onesports.models.userData;
import com.onesports.utilities.Utils;

public class TestData {
	
	// @DataProvider(name="paymentModes")
	// public Object[][] paymentModes() {
  //       return new Object[][] {
  // //          {RazorpayPayMode.CARD, CardType.VISA_CREDIT},
  //   //        {RazorpayPayMode.CARD, CardType.MASTERCARD_DEBIT},
  //           {RazorpayPayMode.UPI, null},
  //           {RazorpayPayMode.NETBANKING, null}
  //       };
  //   }
	
	
	@DataProvider(name="searchInputs")
	public Object[][] searchInputs() {
	
		return new Object[][] {
			
			{"Viven",true},		//valid search
			{"zzz",false},		//invalid search
			{"ViVen",true},		//casesensit
			{"    Viven   ",true}, //leading and training spaces
			{"@",true},				// existing special character	
			{"&*&%#",false},		// invalid especial chars
			
			
		};
}
	
	@DataProvider(name = "invalidRegistrationData")
	public Object[][] invalidRegistrationData() {
	    return new Object[][] {
	        {"Email", "test", "Enter a valid email."},
	        {"Phone", "919291919", "Enter valid 10-digit mobile no."},
	        {"Email", "test@yopmail", "Enter a valid email."}
	    };
	}
	
	@DataProvider(name = "testEmptyFieldsValidation")
	public Object[][] testEmptyFieldsValidation() {
	    return new Object[][] {
	    	{"Name", "", "Name required."},
	        {"Email", "", "Email required."},
	        {"Phone", "", "Mobile no required."},
	        {"Password", "", "Password required."}
	    };
	}
	
	@DataProvider(name = "courseSearchInputs")
	public Object[][] courseSearchInputs() {
		
		return new Object[][] {
			
			{"Football",true},		//valid search
			{"zzzzz",false},		//invalid search
			{"footBALL",true},		//casesensit
			{"    Football   ",false}, //leading and training spaces
			{"&*&%#",false},		// invalid especial chars
			
			
		};
}

@DataProvider(name = "enquiryInvalidMobileData")
    public Object[][] invalidMobileData() {
        return new Object[][]{
          {"9234567890", ""},
                {"12345", "Please enter a valid Indian mobile number (10 digits starting with 6-9)"},
                {"123456789012", "Please enter a valid Indian mobile number (10 digits starting with 6-9)"},
                 {"abcdefgijk", "Mobile number is required"},
                  
                 {"", "Mobile number is required"}
        };
    }

	@DataProvider(name = "nameFieldData")
	public Object[][] nameFieldData() {
    return new Object[][] {

      {"Sunil Nageshwara Rao Renati",""},
        {"12312","Name can contain only alphabets"},
          {"","Name is required"},
            {"sunil@","Name can contain only alphabets"},
                {"sunil12","Name can contain only alphabets"},
                  {",","Name can contain only alphabets"}
  
    };
  }

    	@DataProvider(name = "SurnameFieldData")
	public Object[][] SurnameFieldData() {
    return new Object[][] {

      {"Sunil Nageshwara Rao Renati",""},
        {"12312","Surname can contain only alphabets"},
          {"","Surname is required"},
            {"sunil@","Surname can contain only alphabets"},
                {"sunil12","Surname can contain only alphabets"},
                  {",","Surname can contain only alphabets"}
  
    };
}
   @DataProvider(name = "enquiryInvalidEmailData")
    public Object[][] enquiryInvalidEmailData() {
        return new Object[][]{
         {"sunilrenati10@gmail.com", ""},
         {"sunilrenati.gmail.com", "Please enter a valid email address (e.g., user@example.com)"},
        
          
       {"sunilrenati.gmail.com", "Please enter a valid email address (e.g., user@example.com)"},
         // Missing local part
        {"@gmail.com", "Please enter a valid email address (e.g., user@example.com)"},
        // Missing domain
        {"sunil@", "Please enter a valid email address (e.g., user@example.com)"},
        // Spaces not allowed
        {"sunil renati@gmail.com", "Please enter a valid email address (e.g., user@example.com)"},
         // Multiple @
        {"sunil@@gmail.com", "Please enter a valid email address (e.g., user@example.com)"},

        // Starting with special char
        {".sunil@gmail.com", "Please enter a valid email address (e.g., user@example.com)"},
         // Ending with special char
        {"sunil.@gmail.com", "Please enter a valid email address (e.g., user@example.com)"},
        // Duplicate dots
        {"sunil..renati@gmail.com", "Please enter a valid email address (e.g., user@example.com)"},
      // Invalid domain characters
        {"sunil@.com", "Please enter a valid email address (e.g., user@example.com)"},
          {"sunil@domain..com", "Please enter a valid email address (e.g., user@example.com)"},
            {"sunil!@gmail.com", "Please enter a valid email address (e.g., user@example.com)"},
            // TLD too short
        {"sunil@gmail.c", "Please enter a valid email address (e.g., user@example.com)"},
       

        // Numeric-only domain
        {"sunil@123.123", "Please enter a valid email address (e.g., user@example.com)"},

        {"", "Email is required"},
        {"   ", "Email is required"},
        {"@", "Please enter a valid email address (e.g., user@example.com)"},
        {".@", "Please enter a valid email address (e.g., user@example.com)"},

           
        };
    }

     @DataProvider(name = "enquiriesData")
    public Object[][] getEnquiriesData() {
    String firstName = Utils.generateFirstName(); 
    String surName  = Utils.generateLastName();
    String fullName  = firstName + " " + surName;
    String mobile= Utils.randomPhone();

        return new Object[][]{

                {
                        fullName,             // full name
                        surName,                // surname
                        firstName + System.currentTimeMillis() + "@yopmail.com",
                           "+1",                         
                        mobile,  
                             "+44"  ,           // mobile
                        "9998887776",             // alt mobile
                        "Telangana",
                        "Hyderabad",              // city
                        "UGB"        // course
                                     
                                           
                }
        };

    }

     @DataProvider(name = "getDuplicateEnquirydata")
    public Object[][] getDuplicateEnquirydata() {

      return new Object[][]{

                {
                         "Shankar c",             // full name
                        "chavan",                // surname
                        "sunilrenati10" + "@gmail.com",
                        "+1",                 
                        "92000278499",     
                        "+44"        ,// mobile
                        "9998887077",             // alt mobile
                        "Telangana",
                        "Hyderabad",              // city
                        "UGB"        // course
                                     
                                           
                }
        };
      }

      @DataProvider(name = "getexistingEnquiryRecord")
    public Object[][] getexistingEnquiryRecord() {

      return new Object[][]{

                {
                        "Dev Gowda Kumar",             // full name
                        "Kumar",                // surname
                        "Dev1766143171343" + "@yopmail.com",
                                        
                        "9808389961",             // mobile
                        "9998887776",             // alt mobile
                        "Telangana",
                        "Hyderabad",              // city
                        "UGB"  ,      
                        "Sree@123"

                                     
                                           
                }
        };
      }

        
   @DataProvider(name = "registrationData")
public Object[][] getRegistrationData() throws InterruptedException {

    String firstName = Utils.generateFirstName(); 
    String surName  = Utils.generateLastName();
    String fullName  = firstName + " " + surName;
    String mobile = Utils.randomPhone();

    userData user = new userData(
            "Mr",
            fullName,
            surName,
            firstName + System.currentTimeMillis() + "@yopmail.com",
            mobile,
            "🇮🇳 +91",
            "9998887776",
            "Telangana",
            "Hyderabad",
            "UGB",
            "Computer",
            "CSE",
            "Sree@123"
    );

    return new Object[][]{
            { user }
    };
}
    
  }
