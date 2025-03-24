package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StateDataFetcher fetcher = new StateDataFetcher();
        fetcher.addState("CA");
        fetcher.addState("TX");

        fetcher.fetchData();

        fetcher.getRecordsMap().forEach((state, employers) -> {
            System.out.println("State: " + state);
            employers.forEach((employer, users) -> {
                System.out.println("  Employer: " + employer);
                users.forEach((ssn, user) -> {
                    System.out.println("    SSN: " + ssn + " → " + user);
                });
            });
        });
    }
}