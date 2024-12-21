package vn.edu.iuh.fit.jpasecurity_customer.backend.enums;

public enum AccountStatus {
    CHECKING("Checking"),
    CHECKED("Checked"),
    ;
    private final String value;

    AccountStatus(String value) {
        this.value = value;
    }
}
