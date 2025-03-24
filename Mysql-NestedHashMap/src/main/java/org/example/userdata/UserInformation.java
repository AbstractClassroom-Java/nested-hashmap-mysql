package org.example.userdata;

public record UserInformation(
        String firstName,
        String lastName,
        String ssn,
        String state,
        String employer
) {
    public String toString(int indentLevel) {
        String indent = "\t".repeat(Math.max(0, indentLevel));
        return String.format(
                "%sFirst Name: %s%n%sLast Name: %s%n%sSSN: %s%n%sState: %s%n%sEmployer: %s",
                indent, firstName,
                indent, lastName,
                indent, ssn,
                indent, state,
                indent, employer
        );
    }

    public String toString() {
        return toString(0);
    }
}
