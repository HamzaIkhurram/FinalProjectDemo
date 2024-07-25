package com.subwaysimulator.advertisement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementDatabase {
    private static final String DB_URL = "jdbc:sqlite:advertisements.db";

    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");

                // Create advertisements table
                String sql = "CREATE TABLE IF NOT EXISTS advertisements (\n"
                        + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " type TEXT NOT NULL,\n"
                        + " content BLOB NOT NULL,\n"
                        + " display_time INTEGER NOT NULL\n"
                        + ");";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertAdvertisement(String type, byte[] content, int displayTime) {
        String sql = "INSERT INTO advertisements(type, content, display_time) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, type);
            pstmt.setBytes(2, content);
            pstmt.setInt(3, displayTime);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<byte[]> fetchAdvertisements() {
        List<byte[]> ads = new ArrayList<>();
        String sql = "SELECT content FROM advertisements";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ads.add(rs.getBytes("content"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ads;
    }

    // Additional methods to manage advertisements
}
