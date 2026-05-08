package com.onesports.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.WebDriver;

public class PaymentUtils {

    static {
        RestAssured.baseURI = "https://suadm-stg.suh.edu.in";
    }

    // STEP 1 - Initiate payment
   public static int initiatePayment(WebDriver driver) {

    RequestSpecification request = given();

    // pass selenium session cookies
    for (org.openqa.selenium.Cookie cookie :
            driver.manage().getCookies()) {

        request.cookie(
                cookie.getName(),
                cookie.getValue());
    }

    Response response = request
            .contentType(ContentType.JSON)
            .body("""
                    {
                        "amount": 1100,
                        "purpose": "Application Fee"
                    }
                    """)
            .when()
            .post("https://suadm-stg.suh.edu.in/api/payment/initiate");

    System.out.println(response.asPrettyString());

    response.then().statusCode(200);

    int paymentId =
            response.jsonPath().getInt("payment_id");

    return paymentId;
}
    // STEP 2 - Check payment status
    public static String getPaymentStatus(int paymentId) {

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("payment_id", paymentId)
                .when()
                .get("/api/payment/status")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Payment Status Response:");
        System.out.println(response.asPrettyString());

        String paymentStatus =
                response.jsonPath().getString("paymentStatus");

        System.out.println("Payment Status : " + paymentStatus);

        return paymentStatus;
    }
}