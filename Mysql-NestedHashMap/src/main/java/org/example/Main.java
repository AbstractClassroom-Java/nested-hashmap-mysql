package org.example;

import org.example.userdata.StateDataFetcher;
import org.example.userdata.UserInformation;

public class Main {
    public static void main(String[] args) {
        StateDataFetcher fetcher = new StateDataFetcher();
        fetcher.addState("CA");
        fetcher.addState("TX");

        fetcher.fetchData();

        fetcher.getRecordsMap().forEach((state, employers) -> {
            System.out.println("State: " + state);
            employers.forEach((employer, users) -> {
                System.out.println("\tEmployer: " + employer);
                users.forEach((ssn, user) -> {
                    System.out.println("\t\tSSN: " + ssn);
                    System.out.println(user.toString(3)); // Indent level 2 = two tabs
                });
            });
        });
    }
}
