package org.example.userdata;

import java.sql.*;
import java.util.*;

public class StateDataFetcher {

    // Remote MySQL database details (change these!)
    private static final String DB_URL = "jdbc:mysql://cse-335-spring-2025.cluster-c924km8o85q2.us-east-1.rds.amazonaws.com";
    private static final String DB_USER = "readonly_user";
    private static final String DB_PASSWORD = "StrongPassword123!";

    // Track which states to fetch
    private final Set<String> statesToFetch = new HashSet<>();

    // Nested map: state → employer → ssn → UserInformation
    private final Map<String, Map<String, Map<String, UserInformation>>> recordsMap = new HashMap<>();


    // Add a state to filter
    public void addState(String state) {
        statesToFetch.add(state);
    }

    // Fetch data from the remote database
    public void fetchData() {
        if (statesToFetch.isEmpty()) {
            System.out.println("No states specified.");
            return;
        }

        // Build SQL query with placeholders
        String placeholders = String.join(",", Collections.nCopies(statesToFetch.size(), "?"));
        String sql = "SELECT firstName, lastName, ssn, state, employer FROM fake_data_nathan.users WHERE state IN (" + placeholders + ")";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int i = 1;
            for (String state : statesToFetch) {
                stmt.setString(i++, state);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String ssn = rs.getString("ssn");
                String state = rs.getString("state");
                String employer = rs.getString("employer");

                UserInformation user = new UserInformation(firstName, lastName, ssn, state, employer);

                recordsMap
                        .computeIfAbsent(state, k -> new HashMap<>())
                        .computeIfAbsent(employer, k -> new HashMap<>())
                        .put(ssn, user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Map<String, Map<String, UserInformation>>> getRecordsMap() {
        return recordsMap;
    }

}
