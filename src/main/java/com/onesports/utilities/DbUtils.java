package com.onesports.utilities;

import java.sql.*;

public class DbUtils {

    private static final String URL  = "jdbc:mysql://suadm-stg.suh.edu.in:3306/suadm";
    private static final String USER = "admin";
    private static final String PASS = "n3gzhvxY09dlp531uA7D94@";

    // Load driver once (optional for newer JDBC, but harmless)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    
    private DbUtils() {
        // utility class
    }

  

    // public static String getLatestOtp(String phoneNumber) {

    //       // Normalize phone number
    // if (!phoneNumber.startsWith("+91")) {
    //     phoneNumber = "+91" + phoneNumber;
    // }


    //     String sql =
    //             "SELECT otp_code " +
    //             "FROM otps " +
    //             "WHERE phone_number = ? " +
    //             "ORDER BY created_at DESC " +
    //             "LIMIT 1";

    //     try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
    //          PreparedStatement ps = conn.prepareStatement(sql)) {

    //         ps.setString(1, phoneNumber);

    //         try (ResultSet rs = ps.executeQuery()) {
    //             if (rs.next()) {
    //                 return rs.getString("otp_code");
    //             } else {
    //                 throw new RuntimeException("No OTP found for phone: " + phoneNumber);
    //             }
    //         }

    //     } catch (SQLException e) {
    //         throw new RuntimeException("Error fetching OTP from DB", e);
    //     }
    // }

    public static String getLatestOtp(String phoneNumber) {

    if (!phoneNumber.startsWith("+91")) {
        phoneNumber = "+91" + phoneNumber;
    }

    String sql =
            "SELECT otp_code " +
            "FROM otps " +
            "WHERE phone_number = ? " +
            "ORDER BY created_at DESC " +
            "LIMIT 1";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, phoneNumber);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getString("otp_code");
            }

            return null;
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error fetching OTP from DB", e);
    }
}

public static String waitForLatestOtp(String phoneNumber) {

    for (int i = 1; i <= 10; i++) {

        String otp = getLatestOtp(phoneNumber);

        if (otp != null && !otp.trim().isEmpty()) {
            System.out.println("OTP Found: " + otp);
            return otp;
        }

        System.out.println("OTP not found. Retry " + i + "/10");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    throw new RuntimeException(
            "No OTP found for phone after waiting: " + phoneNumber);
}

    public static String getLatestEmailOtp(String email) {

    String sql =
        "SELECT otp_code " +
        "FROM otps " +
        "WHERE email = ? " +
        "ORDER BY created_at DESC " +
        "LIMIT 1";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email.toLowerCase().trim());

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("otp_code");
            } else {
                throw new RuntimeException("No OTP found for email: " + email);
            }
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error fetching EMAIL OTP from DB", e);
    }
}

public static void resetCompletedSteps(int applicationId) {

    String sql = """
        UPDATE suadm.applications
        SET entrance_exams = JSON_SET(
            entrance_exams,
            '$.completedSteps',
            JSON_ARRAY(false,false,false,false,false,false)
        )
        WHERE id = ?
        """;

    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, applicationId);

        int rowsAffected = ps.executeUpdate();
        System.out.println("Rows updated: " + rowsAffected);

    } catch (SQLException e) {
        throw new RuntimeException("Error resetting completedSteps for applicationId: " + applicationId, e);
    }
}

}



